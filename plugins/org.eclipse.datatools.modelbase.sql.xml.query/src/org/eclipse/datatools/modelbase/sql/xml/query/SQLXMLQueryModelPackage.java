/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLXMLQueryModelPackage.java,v 1.5 2010/02/25 02:13:18 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query;


import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
 * The documentation for this model is adapted from "Information technology -- Database languages -- SQL, Part 14: XML-Related Specifications (SQL/XML)" WG3:WLG-010preview H2-2005-xxx, FDIS ISO/IEC 9075-14:2005 (E) draft published May, 2005.
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelFactory
 * @model kind="package"
 * @generated
 */
public interface SQLXMLQueryModelPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "query";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http:///org/eclipse/datatools/modelbase/sql/xml/query/SQLXMLQueryModel.ecore";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "org.eclipse.datatools.modelbase.sql.xml.query";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    SQLXMLQueryModelPackage eINSTANCE = org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl.init();

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionImpl <em>XML Value Function</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunction()
     * @generated
     */
    int XML_VALUE_FUNCTION = 1;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__EANNOTATIONS = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__NAME = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__DEPENDENCIES = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__DESCRIPTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__LABEL = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION__COMMENTS = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__EXTENSIONS = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION__PRIVILEGES = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__UNARY_OPERATOR = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__DATA_TYPE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUES_ROW = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__RESULT_COLUMN = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__BASIC_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__BASIC_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__LIKE_PATTERN = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__LIKE_MATCHING = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__PREDICATE_NULL = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__BETWEEN_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__BETWEEN_RIGHT1 = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__BETWEEN_RIGHT2 = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_CAST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__GROUPING_EXPR = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__LIKE_ESCAPE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__NEST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__TABLE_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__VALUE_EXPR_ROW = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__CALL_STATEMENT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__SPECIAL_REGISTER = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__DISTINCT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__COLUMN_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__PARAMETER_LIST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION__FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__FUNCTION;

	/**
     * The number of structural features of the '<em>XML Value Function</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FEATURE_COUNT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionConcatImpl <em>XML Value Function Concat</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionConcatImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionConcat()
     * @generated
     */
    int XML_VALUE_FUNCTION_CONCAT = 0;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__EANNOTATIONS = XML_VALUE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__NAME = XML_VALUE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__DEPENDENCIES = XML_VALUE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__DESCRIPTION = XML_VALUE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__LABEL = XML_VALUE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_CONCAT__COMMENTS = XML_VALUE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__EXTENSIONS = XML_VALUE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_CONCAT__PRIVILEGES = XML_VALUE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__UNARY_OPERATOR = XML_VALUE_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__DATA_TYPE = XML_VALUE_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUES_ROW = XML_VALUE_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__ORDER_BY_VALUE_EXPR = XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__RESULT_COLUMN = XML_VALUE_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__BASIC_RIGHT = XML_VALUE_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__BASIC_LEFT = XML_VALUE_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__LIKE_PATTERN = XML_VALUE_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__LIKE_MATCHING = XML_VALUE_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__PREDICATE_NULL = XML_VALUE_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__IN_VALUE_LIST_RIGHT = XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__IN_VALUE_LIST_LEFT = XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__IN_VALUE_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__IN_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__QUANTIFIED_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__QUANTIFIED_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__BETWEEN_LEFT = XML_VALUE_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__BETWEEN_RIGHT1 = XML_VALUE_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__BETWEEN_RIGHT2 = XML_VALUE_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_CAST = XML_VALUE_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_FUNCTION = XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_COMBINED_LEFT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_COMBINED_RIGHT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__GROUPING_EXPR = XML_VALUE_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_CASE_ELSE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_CASE_SIMPLE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__LIKE_ESCAPE = XML_VALUE_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_LABELED_DURATION = XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__NEST = XML_VALUE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__UPDATE_SOURCE_EXPR_LIST = XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__TABLE_FUNCTION = XML_VALUE_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__VALUE_EXPR_ROW = XML_VALUE_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__CALL_STATEMENT = XML_VALUE_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__SPECIAL_REGISTER = XML_VALUE_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__DISTINCT = XML_VALUE_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__COLUMN_FUNCTION = XML_VALUE_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__PARAMETER_LIST = XML_VALUE_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__FUNCTION = XML_VALUE_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Returning Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__RETURNING_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Concat Content List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT__CONCAT_CONTENT_LIST = XML_VALUE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Concat</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_FEATURE_COUNT = XML_VALUE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationItemImpl <em>XML Namespace Declaration Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationItemImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLNamespaceDeclarationItem()
     * @generated
     */
    int XML_NAMESPACE_DECLARATION_ITEM = 6;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_ITEM__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_ITEM__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_ITEM__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_ITEM__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_ITEM__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_NAMESPACE_DECLARATION_ITEM__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_ITEM__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_NAMESPACE_DECLARATION_ITEM__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_ITEM__URI = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Namespaces Decl</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Namespace Declaration Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_ITEM_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationPrefixImpl <em>XML Namespace Declaration Prefix</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationPrefixImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLNamespaceDeclarationPrefix()
     * @generated
     */
    int XML_NAMESPACE_DECLARATION_PREFIX = 2;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_PREFIX__EANNOTATIONS = XML_NAMESPACE_DECLARATION_ITEM__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_PREFIX__NAME = XML_NAMESPACE_DECLARATION_ITEM__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_PREFIX__DEPENDENCIES = XML_NAMESPACE_DECLARATION_ITEM__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_PREFIX__DESCRIPTION = XML_NAMESPACE_DECLARATION_ITEM__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_PREFIX__LABEL = XML_NAMESPACE_DECLARATION_ITEM__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_NAMESPACE_DECLARATION_PREFIX__COMMENTS = XML_NAMESPACE_DECLARATION_ITEM__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_PREFIX__EXTENSIONS = XML_NAMESPACE_DECLARATION_ITEM__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_NAMESPACE_DECLARATION_PREFIX__PRIVILEGES = XML_NAMESPACE_DECLARATION_ITEM__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_PREFIX__URI = XML_NAMESPACE_DECLARATION_ITEM__URI;

	/**
     * The feature id for the '<em><b>Namespaces Decl</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_PREFIX__NAMESPACES_DECL = XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL;

	/**
     * The feature id for the '<em><b>Prefix</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_PREFIX__PREFIX = XML_NAMESPACE_DECLARATION_ITEM_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>XML Namespace Declaration Prefix</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_PREFIX_FEATURE_COUNT = XML_NAMESPACE_DECLARATION_ITEM_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationDefaultImpl <em>XML Namespace Declaration Default</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationDefaultImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLNamespaceDeclarationDefault()
     * @generated
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT = 3;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT__EANNOTATIONS = XML_NAMESPACE_DECLARATION_ITEM__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT__NAME = XML_NAMESPACE_DECLARATION_ITEM__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT__DEPENDENCIES = XML_NAMESPACE_DECLARATION_ITEM__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT__DESCRIPTION = XML_NAMESPACE_DECLARATION_ITEM__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT__LABEL = XML_NAMESPACE_DECLARATION_ITEM__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_NAMESPACE_DECLARATION_DEFAULT__COMMENTS = XML_NAMESPACE_DECLARATION_ITEM__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT__EXTENSIONS = XML_NAMESPACE_DECLARATION_ITEM__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_NAMESPACE_DECLARATION_DEFAULT__PRIVILEGES = XML_NAMESPACE_DECLARATION_ITEM__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT__URI = XML_NAMESPACE_DECLARATION_ITEM__URI;

	/**
     * The feature id for the '<em><b>Namespaces Decl</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT__NAMESPACES_DECL = XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL;

	/**
     * The feature id for the '<em><b>No Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT__NO_DEFAULT = XML_NAMESPACE_DECLARATION_ITEM_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>XML Namespace Declaration Default</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACE_DECLARATION_DEFAULT_FEATURE_COUNT = XML_NAMESPACE_DECLARATION_ITEM_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributeDeclarationItemImpl <em>XML Attribute Declaration Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributeDeclarationItemImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLAttributeDeclarationItem()
     * @generated
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM = 4;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_ATTRIBUTE_DECLARATION_ITEM__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_ATTRIBUTE_DECLARATION_ITEM__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Attributes Decl</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Attribute Declaration Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTE_DECLARATION_ITEM_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementImpl <em>XML Value Function Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionElement()
     * @generated
     */
    int XML_VALUE_FUNCTION_ELEMENT = 5;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__EANNOTATIONS = XML_VALUE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__NAME = XML_VALUE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__DEPENDENCIES = XML_VALUE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__DESCRIPTION = XML_VALUE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__LABEL = XML_VALUE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_ELEMENT__COMMENTS = XML_VALUE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__EXTENSIONS = XML_VALUE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_ELEMENT__PRIVILEGES = XML_VALUE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__UNARY_OPERATOR = XML_VALUE_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__DATA_TYPE = XML_VALUE_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUES_ROW = XML_VALUE_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__ORDER_BY_VALUE_EXPR = XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__RESULT_COLUMN = XML_VALUE_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__BASIC_RIGHT = XML_VALUE_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__BASIC_LEFT = XML_VALUE_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__LIKE_PATTERN = XML_VALUE_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__LIKE_MATCHING = XML_VALUE_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__PREDICATE_NULL = XML_VALUE_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__IN_VALUE_LIST_RIGHT = XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__IN_VALUE_LIST_LEFT = XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__IN_VALUE_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__IN_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__QUANTIFIED_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__QUANTIFIED_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__BETWEEN_LEFT = XML_VALUE_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__BETWEEN_RIGHT1 = XML_VALUE_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__BETWEEN_RIGHT2 = XML_VALUE_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_CAST = XML_VALUE_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_FUNCTION = XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_COMBINED_LEFT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_COMBINED_RIGHT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__GROUPING_EXPR = XML_VALUE_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_CASE_ELSE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_CASE_SIMPLE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__LIKE_ESCAPE = XML_VALUE_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_LABELED_DURATION = XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__NEST = XML_VALUE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__UPDATE_SOURCE_EXPR_LIST = XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__TABLE_FUNCTION = XML_VALUE_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__VALUE_EXPR_ROW = XML_VALUE_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__CALL_STATEMENT = XML_VALUE_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__SPECIAL_REGISTER = XML_VALUE_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__DISTINCT = XML_VALUE_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__COLUMN_FUNCTION = XML_VALUE_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__PARAMETER_LIST = XML_VALUE_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__FUNCTION = XML_VALUE_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Element Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__ELEMENT_NAME = XML_VALUE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Returning Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__RETURNING_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Namespaces Decl</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL = XML_VALUE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Attributes Decl</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL = XML_VALUE_FUNCTION_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Element Content List</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST = XML_VALUE_FUNCTION_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>XML Value Function Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_FEATURE_COUNT = XML_VALUE_FUNCTION_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentItemImpl <em>XML Value Function Element Content Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentItemImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionElementContentItem()
     * @generated
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM = 7;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Element Content List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__ELEMENT_CONTENT_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Element Content Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestImpl <em>XML Value Function Forest</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionForest()
     * @generated
     */
    int XML_VALUE_FUNCTION_FOREST = 8;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__EANNOTATIONS = XML_VALUE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__NAME = XML_VALUE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__DEPENDENCIES = XML_VALUE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__DESCRIPTION = XML_VALUE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__LABEL = XML_VALUE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_FOREST__COMMENTS = XML_VALUE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__EXTENSIONS = XML_VALUE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_FOREST__PRIVILEGES = XML_VALUE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__UNARY_OPERATOR = XML_VALUE_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__DATA_TYPE = XML_VALUE_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUES_ROW = XML_VALUE_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__ORDER_BY_VALUE_EXPR = XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__RESULT_COLUMN = XML_VALUE_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__BASIC_RIGHT = XML_VALUE_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__BASIC_LEFT = XML_VALUE_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__LIKE_PATTERN = XML_VALUE_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__LIKE_MATCHING = XML_VALUE_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__PREDICATE_NULL = XML_VALUE_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__IN_VALUE_LIST_RIGHT = XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__IN_VALUE_LIST_LEFT = XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__IN_VALUE_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__IN_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__QUANTIFIED_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__QUANTIFIED_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__BETWEEN_LEFT = XML_VALUE_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__BETWEEN_RIGHT1 = XML_VALUE_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__BETWEEN_RIGHT2 = XML_VALUE_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_CAST = XML_VALUE_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_FUNCTION = XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_COMBINED_LEFT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_COMBINED_RIGHT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__GROUPING_EXPR = XML_VALUE_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_CASE_ELSE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_CASE_SIMPLE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__LIKE_ESCAPE = XML_VALUE_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_LABELED_DURATION = XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__NEST = XML_VALUE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__UPDATE_SOURCE_EXPR_LIST = XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__TABLE_FUNCTION = XML_VALUE_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__VALUE_EXPR_ROW = XML_VALUE_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__CALL_STATEMENT = XML_VALUE_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__SPECIAL_REGISTER = XML_VALUE_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__DISTINCT = XML_VALUE_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__COLUMN_FUNCTION = XML_VALUE_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__PARAMETER_LIST = XML_VALUE_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__FUNCTION = XML_VALUE_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Null Handling Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__NULL_HANDLING_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Returning Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__RETURNING_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Forest Content List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__FOREST_CONTENT_LIST = XML_VALUE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Namespaces Decl</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL = XML_VALUE_FUNCTION_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>XML Value Function Forest</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_FEATURE_COUNT = XML_VALUE_FUNCTION_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionCommentImpl <em>XML Value Function Comment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionCommentImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionComment()
     * @generated
     */
    int XML_VALUE_FUNCTION_COMMENT = 9;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__EANNOTATIONS = XML_VALUE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__NAME = XML_VALUE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__DEPENDENCIES = XML_VALUE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__DESCRIPTION = XML_VALUE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__LABEL = XML_VALUE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_COMMENT__COMMENTS = XML_VALUE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__EXTENSIONS = XML_VALUE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_COMMENT__PRIVILEGES = XML_VALUE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__UNARY_OPERATOR = XML_VALUE_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__DATA_TYPE = XML_VALUE_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUES_ROW = XML_VALUE_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__ORDER_BY_VALUE_EXPR = XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__RESULT_COLUMN = XML_VALUE_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__BASIC_RIGHT = XML_VALUE_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__BASIC_LEFT = XML_VALUE_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__LIKE_PATTERN = XML_VALUE_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__LIKE_MATCHING = XML_VALUE_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__PREDICATE_NULL = XML_VALUE_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__IN_VALUE_LIST_RIGHT = XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__IN_VALUE_LIST_LEFT = XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__IN_VALUE_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__IN_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__QUANTIFIED_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__QUANTIFIED_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__BETWEEN_LEFT = XML_VALUE_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__BETWEEN_RIGHT1 = XML_VALUE_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__BETWEEN_RIGHT2 = XML_VALUE_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_CAST = XML_VALUE_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_FUNCTION = XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_COMBINED_LEFT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_COMBINED_RIGHT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__GROUPING_EXPR = XML_VALUE_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_CASE_ELSE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_CASE_SIMPLE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__LIKE_ESCAPE = XML_VALUE_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_LABELED_DURATION = XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__NEST = XML_VALUE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__UPDATE_SOURCE_EXPR_LIST = XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__TABLE_FUNCTION = XML_VALUE_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__VALUE_EXPR_ROW = XML_VALUE_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__CALL_STATEMENT = XML_VALUE_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__SPECIAL_REGISTER = XML_VALUE_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__DISTINCT = XML_VALUE_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__COLUMN_FUNCTION = XML_VALUE_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__PARAMETER_LIST = XML_VALUE_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__FUNCTION = XML_VALUE_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Returning Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__RETURNING_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Comment Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT = XML_VALUE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Comment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_FEATURE_COUNT = XML_VALUE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionDocumentImpl <em>XML Value Function Document</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionDocumentImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionDocument()
     * @generated
     */
    int XML_VALUE_FUNCTION_DOCUMENT = 10;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__EANNOTATIONS = XML_VALUE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__NAME = XML_VALUE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__DEPENDENCIES = XML_VALUE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__DESCRIPTION = XML_VALUE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__LABEL = XML_VALUE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_DOCUMENT__COMMENTS = XML_VALUE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__EXTENSIONS = XML_VALUE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_DOCUMENT__PRIVILEGES = XML_VALUE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__UNARY_OPERATOR = XML_VALUE_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__DATA_TYPE = XML_VALUE_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUES_ROW = XML_VALUE_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__ORDER_BY_VALUE_EXPR = XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__RESULT_COLUMN = XML_VALUE_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__BASIC_RIGHT = XML_VALUE_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__BASIC_LEFT = XML_VALUE_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__LIKE_PATTERN = XML_VALUE_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__LIKE_MATCHING = XML_VALUE_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__PREDICATE_NULL = XML_VALUE_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__IN_VALUE_LIST_RIGHT = XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__IN_VALUE_LIST_LEFT = XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__IN_VALUE_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__IN_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__QUANTIFIED_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__QUANTIFIED_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__BETWEEN_LEFT = XML_VALUE_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__BETWEEN_RIGHT1 = XML_VALUE_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__BETWEEN_RIGHT2 = XML_VALUE_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_CAST = XML_VALUE_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_FUNCTION = XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_COMBINED_LEFT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_COMBINED_RIGHT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__GROUPING_EXPR = XML_VALUE_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_CASE_ELSE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_CASE_SIMPLE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__LIKE_ESCAPE = XML_VALUE_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_LABELED_DURATION = XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__NEST = XML_VALUE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__UPDATE_SOURCE_EXPR_LIST = XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__TABLE_FUNCTION = XML_VALUE_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__VALUE_EXPR_ROW = XML_VALUE_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__CALL_STATEMENT = XML_VALUE_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__SPECIAL_REGISTER = XML_VALUE_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__DISTINCT = XML_VALUE_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__COLUMN_FUNCTION = XML_VALUE_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__PARAMETER_LIST = XML_VALUE_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__FUNCTION = XML_VALUE_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Returning Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__RETURNING_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Document Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT__DOCUMENT_CONTENT = XML_VALUE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Document</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_FEATURE_COUNT = XML_VALUE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseImpl <em>XML Value Function Parse</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionParse()
     * @generated
     */
    int XML_VALUE_FUNCTION_PARSE = 11;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__EANNOTATIONS = XML_VALUE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__NAME = XML_VALUE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__DEPENDENCIES = XML_VALUE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__DESCRIPTION = XML_VALUE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__LABEL = XML_VALUE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_PARSE__COMMENTS = XML_VALUE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__EXTENSIONS = XML_VALUE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_PARSE__PRIVILEGES = XML_VALUE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__UNARY_OPERATOR = XML_VALUE_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__DATA_TYPE = XML_VALUE_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUES_ROW = XML_VALUE_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__ORDER_BY_VALUE_EXPR = XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__RESULT_COLUMN = XML_VALUE_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__BASIC_RIGHT = XML_VALUE_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__BASIC_LEFT = XML_VALUE_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__LIKE_PATTERN = XML_VALUE_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__LIKE_MATCHING = XML_VALUE_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__PREDICATE_NULL = XML_VALUE_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__IN_VALUE_LIST_RIGHT = XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__IN_VALUE_LIST_LEFT = XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__IN_VALUE_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__IN_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__QUANTIFIED_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__QUANTIFIED_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__BETWEEN_LEFT = XML_VALUE_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__BETWEEN_RIGHT1 = XML_VALUE_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__BETWEEN_RIGHT2 = XML_VALUE_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_CAST = XML_VALUE_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_FUNCTION = XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_COMBINED_LEFT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_COMBINED_RIGHT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__GROUPING_EXPR = XML_VALUE_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_CASE_ELSE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_CASE_SIMPLE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__LIKE_ESCAPE = XML_VALUE_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_LABELED_DURATION = XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__NEST = XML_VALUE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__UPDATE_SOURCE_EXPR_LIST = XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__TABLE_FUNCTION = XML_VALUE_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__VALUE_EXPR_ROW = XML_VALUE_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__CALL_STATEMENT = XML_VALUE_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__SPECIAL_REGISTER = XML_VALUE_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__DISTINCT = XML_VALUE_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__COLUMN_FUNCTION = XML_VALUE_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__PARAMETER_LIST = XML_VALUE_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__FUNCTION = XML_VALUE_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Content Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__CONTENT_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Whitespace Handling Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__WHITESPACE_HANDLING_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Parse Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT = XML_VALUE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>XML Value Function Parse</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_FEATURE_COUNT = XML_VALUE_FUNCTION_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionPIImpl <em>XML Value Function PI</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionPIImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionPI()
     * @generated
     */
    int XML_VALUE_FUNCTION_PI = 12;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__EANNOTATIONS = XML_VALUE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__NAME = XML_VALUE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__DEPENDENCIES = XML_VALUE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__DESCRIPTION = XML_VALUE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__LABEL = XML_VALUE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_PI__COMMENTS = XML_VALUE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__EXTENSIONS = XML_VALUE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_PI__PRIVILEGES = XML_VALUE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__UNARY_OPERATOR = XML_VALUE_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__DATA_TYPE = XML_VALUE_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUES_ROW = XML_VALUE_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__ORDER_BY_VALUE_EXPR = XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__RESULT_COLUMN = XML_VALUE_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__BASIC_RIGHT = XML_VALUE_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__BASIC_LEFT = XML_VALUE_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__LIKE_PATTERN = XML_VALUE_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__LIKE_MATCHING = XML_VALUE_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__PREDICATE_NULL = XML_VALUE_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__IN_VALUE_LIST_RIGHT = XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__IN_VALUE_LIST_LEFT = XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__IN_VALUE_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__IN_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__QUANTIFIED_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__QUANTIFIED_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__BETWEEN_LEFT = XML_VALUE_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__BETWEEN_RIGHT1 = XML_VALUE_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__BETWEEN_RIGHT2 = XML_VALUE_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_CAST = XML_VALUE_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_FUNCTION = XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_COMBINED_LEFT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_COMBINED_RIGHT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__GROUPING_EXPR = XML_VALUE_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_CASE_ELSE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_CASE_SIMPLE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__LIKE_ESCAPE = XML_VALUE_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_LABELED_DURATION = XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__NEST = XML_VALUE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__UPDATE_SOURCE_EXPR_LIST = XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__TABLE_FUNCTION = XML_VALUE_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__VALUE_EXPR_ROW = XML_VALUE_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__CALL_STATEMENT = XML_VALUE_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__SPECIAL_REGISTER = XML_VALUE_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__DISTINCT = XML_VALUE_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__COLUMN_FUNCTION = XML_VALUE_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__PARAMETER_LIST = XML_VALUE_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__FUNCTION = XML_VALUE_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Target Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__TARGET_NAME = XML_VALUE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Returning Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__RETURNING_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>PI Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI__PI_CONTENT = XML_VALUE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>XML Value Function PI</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_FEATURE_COUNT = XML_VALUE_FUNCTION_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryImpl <em>XML Value Function Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionQuery()
     * @generated
     */
    int XML_VALUE_FUNCTION_QUERY = 13;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__EANNOTATIONS = XML_VALUE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__NAME = XML_VALUE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__DEPENDENCIES = XML_VALUE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__DESCRIPTION = XML_VALUE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__LABEL = XML_VALUE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_QUERY__COMMENTS = XML_VALUE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__EXTENSIONS = XML_VALUE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_QUERY__PRIVILEGES = XML_VALUE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__UNARY_OPERATOR = XML_VALUE_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__DATA_TYPE = XML_VALUE_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUES_ROW = XML_VALUE_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__ORDER_BY_VALUE_EXPR = XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__RESULT_COLUMN = XML_VALUE_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__BASIC_RIGHT = XML_VALUE_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__BASIC_LEFT = XML_VALUE_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__LIKE_PATTERN = XML_VALUE_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__LIKE_MATCHING = XML_VALUE_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__PREDICATE_NULL = XML_VALUE_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__IN_VALUE_LIST_RIGHT = XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__IN_VALUE_LIST_LEFT = XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__IN_VALUE_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__IN_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__QUANTIFIED_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__QUANTIFIED_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__BETWEEN_LEFT = XML_VALUE_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__BETWEEN_RIGHT1 = XML_VALUE_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__BETWEEN_RIGHT2 = XML_VALUE_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_CAST = XML_VALUE_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_FUNCTION = XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_COMBINED_LEFT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_COMBINED_RIGHT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__GROUPING_EXPR = XML_VALUE_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_CASE_ELSE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_CASE_SIMPLE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__LIKE_ESCAPE = XML_VALUE_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_LABELED_DURATION = XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__NEST = XML_VALUE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__UPDATE_SOURCE_EXPR_LIST = XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__TABLE_FUNCTION = XML_VALUE_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__VALUE_EXPR_ROW = XML_VALUE_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__CALL_STATEMENT = XML_VALUE_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__SPECIAL_REGISTER = XML_VALUE_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__DISTINCT = XML_VALUE_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__COLUMN_FUNCTION = XML_VALUE_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__PARAMETER_LIST = XML_VALUE_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__FUNCTION = XML_VALUE_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Empty Handling Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__EMPTY_HANDLING_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Xquery Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR = XML_VALUE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Xquery Arg List</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST = XML_VALUE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Query Returning</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING = XML_VALUE_FUNCTION_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>XML Value Function Query</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_FEATURE_COUNT = XML_VALUE_FUNCTION_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionTextImpl <em>XML Value Function Text</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionTextImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionText()
     * @generated
     */
    int XML_VALUE_FUNCTION_TEXT = 14;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__EANNOTATIONS = XML_VALUE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__NAME = XML_VALUE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__DEPENDENCIES = XML_VALUE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__DESCRIPTION = XML_VALUE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__LABEL = XML_VALUE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_TEXT__COMMENTS = XML_VALUE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__EXTENSIONS = XML_VALUE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_TEXT__PRIVILEGES = XML_VALUE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__UNARY_OPERATOR = XML_VALUE_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__DATA_TYPE = XML_VALUE_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUES_ROW = XML_VALUE_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__ORDER_BY_VALUE_EXPR = XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__RESULT_COLUMN = XML_VALUE_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__BASIC_RIGHT = XML_VALUE_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__BASIC_LEFT = XML_VALUE_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__LIKE_PATTERN = XML_VALUE_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__LIKE_MATCHING = XML_VALUE_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__PREDICATE_NULL = XML_VALUE_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__IN_VALUE_LIST_RIGHT = XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__IN_VALUE_LIST_LEFT = XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__IN_VALUE_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__IN_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__QUANTIFIED_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__QUANTIFIED_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__BETWEEN_LEFT = XML_VALUE_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__BETWEEN_RIGHT1 = XML_VALUE_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__BETWEEN_RIGHT2 = XML_VALUE_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_CAST = XML_VALUE_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_FUNCTION = XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_COMBINED_LEFT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_COMBINED_RIGHT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__GROUPING_EXPR = XML_VALUE_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_CASE_ELSE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_CASE_SIMPLE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__LIKE_ESCAPE = XML_VALUE_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_LABELED_DURATION = XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__NEST = XML_VALUE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__UPDATE_SOURCE_EXPR_LIST = XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__TABLE_FUNCTION = XML_VALUE_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__VALUE_EXPR_ROW = XML_VALUE_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__CALL_STATEMENT = XML_VALUE_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__SPECIAL_REGISTER = XML_VALUE_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__DISTINCT = XML_VALUE_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__COLUMN_FUNCTION = XML_VALUE_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__PARAMETER_LIST = XML_VALUE_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__FUNCTION = XML_VALUE_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Returning Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__RETURNING_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Text Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT = XML_VALUE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Text</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_FEATURE_COUNT = XML_VALUE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateImpl <em>XML Value Function Validate</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidate()
     * @generated
     */
    int XML_VALUE_FUNCTION_VALIDATE = 15;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__EANNOTATIONS = XML_VALUE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__NAME = XML_VALUE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__DEPENDENCIES = XML_VALUE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__DESCRIPTION = XML_VALUE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__LABEL = XML_VALUE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE__COMMENTS = XML_VALUE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__EXTENSIONS = XML_VALUE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE__PRIVILEGES = XML_VALUE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__UNARY_OPERATOR = XML_VALUE_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__DATA_TYPE = XML_VALUE_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUES_ROW = XML_VALUE_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__ORDER_BY_VALUE_EXPR = XML_VALUE_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__RESULT_COLUMN = XML_VALUE_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__BASIC_RIGHT = XML_VALUE_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__BASIC_LEFT = XML_VALUE_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__LIKE_PATTERN = XML_VALUE_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__LIKE_MATCHING = XML_VALUE_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__PREDICATE_NULL = XML_VALUE_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__IN_VALUE_LIST_RIGHT = XML_VALUE_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__IN_VALUE_LIST_LEFT = XML_VALUE_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__IN_VALUE_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__IN_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__QUANTIFIED_ROW_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__QUANTIFIED_VALUE_SELECT_LEFT = XML_VALUE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__BETWEEN_LEFT = XML_VALUE_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__BETWEEN_RIGHT1 = XML_VALUE_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__BETWEEN_RIGHT2 = XML_VALUE_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_CAST = XML_VALUE_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_FUNCTION = XML_VALUE_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_COMBINED_LEFT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_COMBINED_RIGHT = XML_VALUE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__GROUPING_EXPR = XML_VALUE_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_CASE_ELSE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_CASE_SIMPLE = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_VALUE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__LIKE_ESCAPE = XML_VALUE_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_LABELED_DURATION = XML_VALUE_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__NEST = XML_VALUE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__UPDATE_SOURCE_EXPR_LIST = XML_VALUE_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__TABLE_FUNCTION = XML_VALUE_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALUE_EXPR_ROW = XML_VALUE_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__CALL_STATEMENT = XML_VALUE_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__SPECIAL_REGISTER = XML_VALUE_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__DISTINCT = XML_VALUE_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__COLUMN_FUNCTION = XML_VALUE_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__PARAMETER_LIST = XML_VALUE_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__FUNCTION = XML_VALUE_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Content Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__CONTENT_OPTION = XML_VALUE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Validate Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT = XML_VALUE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Validate According To</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO = XML_VALUE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>XML Value Function Validate</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_FEATURE_COUNT = XML_VALUE_FUNCTION_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueExpressionCastImpl <em>XML Value Expression Cast</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueExpressionCastImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueExpressionCast()
     * @generated
     */
    int XML_VALUE_EXPRESSION_CAST = 16;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__EANNOTATIONS = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__NAME = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__DEPENDENCIES = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__DESCRIPTION = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__LABEL = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_EXPRESSION_CAST__COMMENTS = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__EXTENSIONS = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_EXPRESSION_CAST__PRIVILEGES = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__UNARY_OPERATOR = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__DATA_TYPE = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUES_ROW = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__RESULT_COLUMN = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__BASIC_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__BASIC_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__LIKE_PATTERN = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__LIKE_MATCHING = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__PREDICATE_NULL = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__BETWEEN_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__BETWEEN_RIGHT1 = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__BETWEEN_RIGHT2 = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_CAST = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__GROUPING_EXPR = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__LIKE_ESCAPE = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__NEST = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__TABLE_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR_ROW = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__CALL_STATEMENT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__VALUE_EXPR = SQLQueryModelPackage.VALUE_EXPRESSION_CAST__VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Passing Mechanism</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST__PASSING_MECHANISM = SQLQueryModelPackage.VALUE_EXPRESSION_CAST_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>XML Value Expression Cast</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_EXPRESSION_CAST_FEATURE_COUNT = SQLQueryModelPackage.VALUE_EXPRESSION_CAST_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateImpl <em>XML Predicate</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPredicate()
     * @generated
     */
    int XML_PREDICATE = 17;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__EANNOTATIONS = SQLQueryModelPackage.PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__NAME = SQLQueryModelPackage.PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__DEPENDENCIES = SQLQueryModelPackage.PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__DESCRIPTION = SQLQueryModelPackage.PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__LABEL = SQLQueryModelPackage.PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_PREDICATE__COMMENTS = SQLQueryModelPackage.PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__EXTENSIONS = SQLQueryModelPackage.PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_PREDICATE__PRIVILEGES = SQLQueryModelPackage.PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__NEGATED_CONDITION = SQLQueryModelPackage.PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__UPDATE_STATEMENT = SQLQueryModelPackage.PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__DELETE_STATEMENT = SQLQueryModelPackage.PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__TABLE_JOINED = SQLQueryModelPackage.PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__COMBINED_LEFT = SQLQueryModelPackage.PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__COMBINED_RIGHT = SQLQueryModelPackage.PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__QUERY_SELECT_HAVING = SQLQueryModelPackage.PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__QUERY_SELECT_WHERE = SQLQueryModelPackage.PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__NEST = SQLQueryModelPackage.PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__MERGE_ON_CONDITION = SQLQueryModelPackage.PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__NEGATED_PREDICATE = SQLQueryModelPackage.PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__HAS_SELECTIVITY = SQLQueryModelPackage.PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE__SELECTIVITY_VALUE = SQLQueryModelPackage.PREDICATE__SELECTIVITY_VALUE;

	/**
     * The number of structural features of the '<em>XML Predicate</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_FEATURE_COUNT = SQLQueryModelPackage.PREDICATE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateContentImpl <em>XML Predicate Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateContentImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPredicateContent()
     * @generated
     */
    int XML_PREDICATE_CONTENT = 18;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__EANNOTATIONS = XML_PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__NAME = XML_PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__DEPENDENCIES = XML_PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__DESCRIPTION = XML_PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__LABEL = XML_PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_PREDICATE_CONTENT__COMMENTS = XML_PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__EXTENSIONS = XML_PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_PREDICATE_CONTENT__PRIVILEGES = XML_PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__NEGATED_CONDITION = XML_PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__UPDATE_STATEMENT = XML_PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__DELETE_STATEMENT = XML_PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__TABLE_JOINED = XML_PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__COMBINED_LEFT = XML_PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__COMBINED_RIGHT = XML_PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__QUERY_SELECT_HAVING = XML_PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__QUERY_SELECT_WHERE = XML_PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__NEST = XML_PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__MERGE_ON_CONDITION = XML_PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__NEGATED_PREDICATE = XML_PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__HAS_SELECTIVITY = XML_PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT__SELECTIVITY_VALUE = XML_PREDICATE__SELECTIVITY_VALUE;

	/**
     * The number of structural features of the '<em>XML Predicate Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_CONTENT_FEATURE_COUNT = XML_PREDICATE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateDocumentImpl <em>XML Predicate Document</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateDocumentImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPredicateDocument()
     * @generated
     */
    int XML_PREDICATE_DOCUMENT = 19;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__EANNOTATIONS = XML_PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__NAME = XML_PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__DEPENDENCIES = XML_PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__DESCRIPTION = XML_PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__LABEL = XML_PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_PREDICATE_DOCUMENT__COMMENTS = XML_PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__EXTENSIONS = XML_PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_PREDICATE_DOCUMENT__PRIVILEGES = XML_PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__NEGATED_CONDITION = XML_PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__UPDATE_STATEMENT = XML_PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__DELETE_STATEMENT = XML_PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__TABLE_JOINED = XML_PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__COMBINED_LEFT = XML_PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__COMBINED_RIGHT = XML_PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__QUERY_SELECT_HAVING = XML_PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__QUERY_SELECT_WHERE = XML_PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__NEST = XML_PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__MERGE_ON_CONDITION = XML_PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__NEGATED_PREDICATE = XML_PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__HAS_SELECTIVITY = XML_PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT__SELECTIVITY_VALUE = XML_PREDICATE__SELECTIVITY_VALUE;

	/**
     * The number of structural features of the '<em>XML Predicate Document</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_DOCUMENT_FEATURE_COUNT = XML_PREDICATE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateExistsImpl <em>XML Predicate Exists</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateExistsImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPredicateExists()
     * @generated
     */
    int XML_PREDICATE_EXISTS = 20;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__EANNOTATIONS = XML_PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__NAME = XML_PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__DEPENDENCIES = XML_PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__DESCRIPTION = XML_PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__LABEL = XML_PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_PREDICATE_EXISTS__COMMENTS = XML_PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__EXTENSIONS = XML_PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_PREDICATE_EXISTS__PRIVILEGES = XML_PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__NEGATED_CONDITION = XML_PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__UPDATE_STATEMENT = XML_PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__DELETE_STATEMENT = XML_PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__TABLE_JOINED = XML_PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__COMBINED_LEFT = XML_PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__COMBINED_RIGHT = XML_PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__QUERY_SELECT_HAVING = XML_PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__QUERY_SELECT_WHERE = XML_PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__NEST = XML_PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__MERGE_ON_CONDITION = XML_PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__NEGATED_PREDICATE = XML_PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__HAS_SELECTIVITY = XML_PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__SELECTIVITY_VALUE = XML_PREDICATE__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Xquery Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__XQUERY_EXPR = XML_PREDICATE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Xquery Arg List</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS__XQUERY_ARG_LIST = XML_PREDICATE_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Predicate Exists</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_EXISTS_FEATURE_COUNT = XML_PREDICATE_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateValidImpl <em>XML Predicate Valid</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateValidImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPredicateValid()
     * @generated
     */
    int XML_PREDICATE_VALID = 21;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__EANNOTATIONS = XML_PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__NAME = XML_PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__DEPENDENCIES = XML_PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__DESCRIPTION = XML_PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__LABEL = XML_PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_PREDICATE_VALID__COMMENTS = XML_PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__EXTENSIONS = XML_PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_PREDICATE_VALID__PRIVILEGES = XML_PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__NEGATED_CONDITION = XML_PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__UPDATE_STATEMENT = XML_PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__DELETE_STATEMENT = XML_PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__TABLE_JOINED = XML_PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__COMBINED_LEFT = XML_PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__COMBINED_RIGHT = XML_PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__QUERY_SELECT_HAVING = XML_PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__QUERY_SELECT_WHERE = XML_PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__VALUE_EXPR_CASE_SEARCH_CONTENT = XML_PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__NEST = XML_PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__MERGE_ON_CONDITION = XML_PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__NEGATED_PREDICATE = XML_PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__HAS_SELECTIVITY = XML_PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID__SELECTIVITY_VALUE = XML_PREDICATE__SELECTIVITY_VALUE;

	/**
     * The number of structural features of the '<em>XML Predicate Valid</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_PREDICATE_VALID_FEATURE_COUNT = XML_PREDICATE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryExpressionImpl <em>XML Query Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryExpressionImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLQueryExpression()
     * @generated
     */
    int XML_QUERY_EXPRESSION = 22;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_EXPRESSION__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_EXPRESSION__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_EXPRESSION__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_EXPRESSION__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_EXPRESSION__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_QUERY_EXPRESSION__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_EXPRESSION__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_QUERY_EXPRESSION__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Xquery Expr Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Predicate Exists</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_EXPRESSION__PREDICATE_EXISTS = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Value Function Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>XML Query Expression</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_EXPRESSION_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl <em>XML Query Argument List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLQueryArgumentList()
     * @generated
     */
    int XML_QUERY_ARGUMENT_LIST = 23;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_QUERY_ARGUMENT_LIST__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_QUERY_ARGUMENT_LIST__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Passing Mechanism</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Predicate Exists</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Xquery Arg List Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Value Function Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>XML Query Argument List</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_LIST_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentItemImpl <em>XML Query Argument Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentItemImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLQueryArgumentItem()
     * @generated
     */
    int XML_QUERY_ARGUMENT_ITEM = 24;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_QUERY_ARGUMENT_ITEM__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_QUERY_ARGUMENT_ITEM__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Passing Mechanism</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__PASSING_MECHANISM = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Xquery Arg List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__XQUERY_ARG_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>XML Query Argument Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_QUERY_ARGUMENT_ITEM_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionImpl <em>XML Serialize Function</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLSerializeFunction()
     * @generated
     */
    int XML_SERIALIZE_FUNCTION = 25;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__EANNOTATIONS = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__NAME = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__DEPENDENCIES = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__DESCRIPTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__LABEL = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_SERIALIZE_FUNCTION__COMMENTS = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__EXTENSIONS = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_SERIALIZE_FUNCTION__PRIVILEGES = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__UNARY_OPERATOR = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__DATA_TYPE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUES_ROW = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__RESULT_COLUMN = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__BASIC_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__BASIC_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__LIKE_PATTERN = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__LIKE_MATCHING = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__PREDICATE_NULL = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__BETWEEN_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT1 = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT2 = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_CAST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__GROUPING_EXPR = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__LIKE_ESCAPE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__NEST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__TABLE_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__VALUE_EXPR_ROW = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__CALL_STATEMENT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__SPECIAL_REGISTER = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__DISTINCT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__COLUMN_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__PARAMETER_LIST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Content Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__CONTENT_OPTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Serialize Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Declaration Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__DECLARATION_OPTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Serialize Target</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Serialize Encoding</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>XML Serialize Function</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_FEATURE_COUNT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionTargetImpl <em>XML Serialize Function Target</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionTargetImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLSerializeFunctionTarget()
     * @generated
     */
    int XML_SERIALIZE_FUNCTION_TARGET = 26;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_SERIALIZE_FUNCTION_TARGET__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_SERIALIZE_FUNCTION_TARGET__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Serialize Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__SERIALIZE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Serialize Function Target</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_TARGET_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAggregateFunctionImpl <em>XML Aggregate Function</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAggregateFunctionImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLAggregateFunction()
     * @generated
     */
    int XML_AGGREGATE_FUNCTION = 27;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__EANNOTATIONS = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__NAME = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__DEPENDENCIES = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__DESCRIPTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__LABEL = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_AGGREGATE_FUNCTION__COMMENTS = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__EXTENSIONS = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_AGGREGATE_FUNCTION__PRIVILEGES = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__UNARY_OPERATOR = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__DATA_TYPE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUES_ROW = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__RESULT_COLUMN = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__BASIC_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__BASIC_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__LIKE_PATTERN = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__LIKE_MATCHING = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__PREDICATE_NULL = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__BETWEEN_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__BETWEEN_RIGHT1 = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__BETWEEN_RIGHT2 = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_CAST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__GROUPING_EXPR = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__LIKE_ESCAPE = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__NEST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__TABLE_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__VALUE_EXPR_ROW = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__CALL_STATEMENT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__SPECIAL_REGISTER = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__DISTINCT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__DISTINCT;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__COLUMN_FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__PARAMETER_LIST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__FUNCTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION__FUNCTION;

	/**
     * The feature id for the '<em><b>Returning Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__RETURNING_OPTION = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Sort Spec List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION__SORT_SPEC_LIST = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Aggregate Function</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_FUNCTION_FEATURE_COUNT = SQLQueryModelPackage.VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionConcatContentItemImpl <em>XML Value Function Concat Content Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionConcatContentItemImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionConcatContentItem()
     * @generated
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM = 28;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Function Concat</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_FUNCTION_CONCAT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Concat Content Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionCommentContentImpl <em>XML Value Function Comment Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionCommentContentImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionCommentContent()
     * @generated
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT = 29;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_COMMENT_CONTENT__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_COMMENT_CONTENT__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Function Comment</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_FUNCTION_COMMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Comment Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_COMMENT_CONTENT_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionDocumentContentImpl <em>XML Value Function Document Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionDocumentContentImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionDocumentContent()
     * @generated
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT = 30;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Function Document</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_FUNCTION_DOCUMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Document Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_DOCUMENT_CONTENT_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAggregateSortSpecificationImpl <em>XML Aggregate Sort Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAggregateSortSpecificationImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLAggregateSortSpecification()
     * @generated
     */
    int XML_AGGREGATE_SORT_SPECIFICATION = 31;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_SORT_SPECIFICATION__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_SORT_SPECIFICATION__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_SORT_SPECIFICATION__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_SORT_SPECIFICATION__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_SORT_SPECIFICATION__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_AGGREGATE_SORT_SPECIFICATION__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_SORT_SPECIFICATION__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_AGGREGATE_SORT_SPECIFICATION__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Aggregate Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Order By Spec</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Aggregate Sort Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_AGGREGATE_SORT_SPECIFICATION_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestContentItemImpl <em>XML Value Function Forest Content Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestContentItemImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionForestContentItem()
     * @generated
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM = 32;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Function Forest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_FUNCTION_FOREST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Forest Content Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseContentImpl <em>XML Value Function Parse Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseContentImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionParseContent()
     * @generated
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT = 33;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_PARSE_CONTENT__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_PARSE_CONTENT__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Function Parse</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_FUNCTION_PARSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Parse Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PARSE_CONTENT_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionPIContentImpl <em>XML Value Function PI Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionPIContentImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionPIContent()
     * @generated
     */
    int XML_VALUE_FUNCTION_PI_CONTENT = 34;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_PI_CONTENT__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_PI_CONTENT__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Function PI</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function PI Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_PI_CONTENT_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableFunctionImpl <em>XML Table Function</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableFunctionImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLTableFunction()
     * @generated
     */
    int XML_TABLE_FUNCTION = 35;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__EANNOTATIONS = SQLQueryModelPackage.TABLE_FUNCTION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__NAME = SQLQueryModelPackage.TABLE_FUNCTION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__DEPENDENCIES = SQLQueryModelPackage.TABLE_FUNCTION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__DESCRIPTION = SQLQueryModelPackage.TABLE_FUNCTION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__LABEL = SQLQueryModelPackage.TABLE_FUNCTION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TABLE_FUNCTION__COMMENTS = SQLQueryModelPackage.TABLE_FUNCTION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__EXTENSIONS = SQLQueryModelPackage.TABLE_FUNCTION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TABLE_FUNCTION__PRIVILEGES = SQLQueryModelPackage.TABLE_FUNCTION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__TABLE_JOINED_RIGHT = SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__TABLE_JOINED_LEFT = SQLQueryModelPackage.TABLE_FUNCTION__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__QUERY_SELECT = SQLQueryModelPackage.TABLE_FUNCTION__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__NEST = SQLQueryModelPackage.TABLE_FUNCTION__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__MERGE_SOURCE_TABLE = SQLQueryModelPackage.TABLE_FUNCTION__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__COLUMN_LIST = SQLQueryModelPackage.TABLE_FUNCTION__COLUMN_LIST;

	/**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__TABLE_CORRELATION = SQLQueryModelPackage.TABLE_FUNCTION__TABLE_CORRELATION;

	/**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS = SQLQueryModelPackage.TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS;

	/**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__VALUE_EXPR_COLUMNS = SQLQueryModelPackage.TABLE_FUNCTION__VALUE_EXPR_COLUMNS;

	/**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__MERGE_TARGET_TABLE = SQLQueryModelPackage.TABLE_FUNCTION__MERGE_TARGET_TABLE;

    /**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__FUNCTION = SQLQueryModelPackage.TABLE_FUNCTION__FUNCTION;

    /**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__PARAMETER_LIST = SQLQueryModelPackage.TABLE_FUNCTION__PARAMETER_LIST;

    /**
     * The feature id for the '<em><b>Table Row Pattern</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__TABLE_ROW_PATTERN = SQLQueryModelPackage.TABLE_FUNCTION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Xquery Arg List</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__XQUERY_ARG_LIST = SQLQueryModelPackage.TABLE_FUNCTION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Column Def List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__COLUMN_DEF_LIST = SQLQueryModelPackage.TABLE_FUNCTION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Namespaces Decl</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION__NAMESPACES_DECL = SQLQueryModelPackage.TABLE_FUNCTION_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>XML Table Function</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_FUNCTION_FEATURE_COUNT = SQLQueryModelPackage.TABLE_FUNCTION_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionTextContentImpl <em>XML Value Function Text Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionTextContentImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionTextContent()
     * @generated
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT = 36;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_TEXT_CONTENT__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_TEXT_CONTENT__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Function Text</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_FUNCTION_TEXT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Text Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_TEXT_CONTENT_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateContentImpl <em>XML Value Function Validate Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateContentImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateContent()
     * @generated
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT = 37;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_CONTENT__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_CONTENT__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Function Validate</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_FUNCTION_VALIDATE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Validate Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_CONTENT_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionItemImpl <em>XML Table Column Definition Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionItemImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLTableColumnDefinitionItem()
     * @generated
     */
    int XML_TABLE_COLUMN_DEFINITION_ITEM = 38;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ITEM__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ITEM__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ITEM__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ITEM__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ITEM__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TABLE_COLUMN_DEFINITION_ITEM__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ITEM__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TABLE_COLUMN_DEFINITION_ITEM__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>XML Table Column Definition Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ITEM_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionRegularImpl <em>XML Table Column Definition Regular</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionRegularImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLTableColumnDefinitionRegular()
     * @generated
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR = 39;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__EANNOTATIONS = XML_TABLE_COLUMN_DEFINITION_ITEM__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__NAME = XML_TABLE_COLUMN_DEFINITION_ITEM__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__DEPENDENCIES = XML_TABLE_COLUMN_DEFINITION_ITEM__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__DESCRIPTION = XML_TABLE_COLUMN_DEFINITION_ITEM__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__LABEL = XML_TABLE_COLUMN_DEFINITION_ITEM__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TABLE_COLUMN_DEFINITION_REGULAR__COMMENTS = XML_TABLE_COLUMN_DEFINITION_ITEM__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__EXTENSIONS = XML_TABLE_COLUMN_DEFINITION_ITEM__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TABLE_COLUMN_DEFINITION_REGULAR__PRIVILEGES = XML_TABLE_COLUMN_DEFINITION_ITEM__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_FUNCTION = XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE = XML_TABLE_COLUMN_DEFINITION_ITEM_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Passing Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION = XML_TABLE_COLUMN_DEFINITION_ITEM_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Table Column Pattern</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN = XML_TABLE_COLUMN_DEFINITION_ITEM_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Column Definition Default</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT = XML_TABLE_COLUMN_DEFINITION_ITEM_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>XML Table Column Definition Regular</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_REGULAR_FEATURE_COUNT = XML_TABLE_COLUMN_DEFINITION_ITEM_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionOrdinalityImpl <em>XML Table Column Definition Ordinality</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionOrdinalityImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLTableColumnDefinitionOrdinality()
     * @generated
     */
    int XML_TABLE_COLUMN_DEFINITION_ORDINALITY = 40;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ORDINALITY__EANNOTATIONS = XML_TABLE_COLUMN_DEFINITION_ITEM__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ORDINALITY__NAME = XML_TABLE_COLUMN_DEFINITION_ITEM__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DEPENDENCIES = XML_TABLE_COLUMN_DEFINITION_ITEM__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ORDINALITY__DESCRIPTION = XML_TABLE_COLUMN_DEFINITION_ITEM__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ORDINALITY__LABEL = XML_TABLE_COLUMN_DEFINITION_ITEM__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TABLE_COLUMN_DEFINITION_ORDINALITY__COMMENTS = XML_TABLE_COLUMN_DEFINITION_ITEM__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ORDINALITY__EXTENSIONS = XML_TABLE_COLUMN_DEFINITION_ITEM__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TABLE_COLUMN_DEFINITION_ORDINALITY__PRIVILEGES = XML_TABLE_COLUMN_DEFINITION_ITEM__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ORDINALITY__TABLE_FUNCTION = XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION;

	/**
     * The number of structural features of the '<em>XML Table Column Definition Ordinality</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_ORDINALITY_FEATURE_COUNT = XML_TABLE_COLUMN_DEFINITION_ITEM_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToImpl <em>XML Value Function Validate According To</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateAccordingTo()
     * @generated
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO = 41;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Value Function Validate</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Validate Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Validate According To</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToURIImpl <em>XML Value Function Validate According To URI</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToURIImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateAccordingToURI()
     * @generated
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI = 42;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__EANNOTATIONS = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NAME = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DEPENDENCIES = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__DESCRIPTION = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__LABEL = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__COMMENTS = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__EXTENSIONS = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__PRIVILEGES = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Value Function Validate</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALUE_FUNCTION_VALIDATE = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE;

	/**
     * The feature id for the '<em><b>Validate Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__VALIDATE_ELEMENT = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT;

	/**
     * The feature id for the '<em><b>No Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Target Namespace URI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Schema Location URI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>XML Value Function Validate According To URI</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI_FEATURE_COUNT = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToIdentifierImpl <em>XML Value Function Validate According To Identifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToIdentifierImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateAccordingToIdentifier()
     * @generated
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER = 43;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__EANNOTATIONS = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__NAME = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DEPENDENCIES = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__DESCRIPTION = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__LABEL = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__COMMENTS = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__EXTENSIONS = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__PRIVILEGES = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Value Function Validate</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALUE_FUNCTION_VALIDATE = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE;

	/**
     * The feature id for the '<em><b>Validate Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__VALIDATE_ELEMENT = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT;

	/**
     * The feature id for the '<em><b>Schema Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Registered XML Schema Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Value Function Validate According To Identifier</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER_FEATURE_COUNT = XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNameImpl <em>XML Value Function Validate Element Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNameImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateElementName()
     * @generated
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME = 44;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Validate Element</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>XML Value Function Validate Element Name</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNamespaceImpl <em>XML Value Function Validate Element Namespace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNamespaceImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateElementNamespace()
     * @generated
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE = 45;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>No Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Namespace URI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Validate Element</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>XML Value Function Validate Element Namespace</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespacesDeclarationImpl <em>XML Namespaces Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespacesDeclarationImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLNamespacesDeclaration()
     * @generated
     */
    int XML_NAMESPACES_DECLARATION = 46;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_NAMESPACES_DECLARATION__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_NAMESPACES_DECLARATION__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Namespace Decltem List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Function Element</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Value Function Forest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION__TABLE_FUNCTION = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>XML Namespaces Declaration</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_NAMESPACES_DECLARATION_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributesDeclarationImpl <em>XML Attributes Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributesDeclarationImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLAttributesDeclaration()
     * @generated
     */
    int XML_ATTRIBUTES_DECLARATION = 47;

	/**
     * The feature id for the '<em><b>Value Function Element</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT = 0;

	/**
     * The feature id for the '<em><b>Attribute Decl Item</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM = 1;

	/**
     * The number of structural features of the '<em>XML Attributes Declaration</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_ATTRIBUTES_DECLARATION_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentListImpl <em>XML Value Function Element Content List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentListImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionElementContentList()
     * @generated
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST = 48;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Null Handling Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NULL_HANDLING_OPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Function Element</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Element Content List Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>XML Value Function Element Content List</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryReturningImpl <em>XML Value Function Query Returning</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryReturningImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionQueryReturning()
     * @generated
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING = 49;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_QUERY_RETURNING__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_QUERY_RETURNING__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Returning Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING__RETURNING_OPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Passing Option</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING__PASSING_OPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Value Function Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>XML Value Function Query Returning</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_QUERY_RETURNING_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementImpl <em>XML Value Function Validate Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateElement()
     * @generated
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT = 50;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Validate Element Namespace</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Validate Element Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Validate According To</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>XML Value Function Validate Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_VALUE_FUNCTION_VALIDATE_ELEMENT_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionDefaultImpl <em>XML Table Column Definition Default</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionDefaultImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLTableColumnDefinitionDefault()
     * @generated
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT = 51;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__EANNOTATIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__NAME = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__DEPENDENCIES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__DESCRIPTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__LABEL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TABLE_COLUMN_DEFINITION_DEFAULT__COMMENTS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__EXTENSIONS = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_TABLE_COLUMN_DEFINITION_DEFAULT__PRIVILEGES = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__UNARY_OPERATOR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__DATA_TYPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUES_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__ORDER_BY_VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__RESULT_COLUMN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__BASIC_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__BASIC_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__LIKE_PATTERN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__LIKE_MATCHING = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__PREDICATE_NULL = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__IN_VALUE_LIST_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__IN_VALUE_LIST_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__IN_VALUE_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__IN_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__QUANTIFIED_ROW_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__QUANTIFIED_VALUE_SELECT_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__BETWEEN_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__BETWEEN_RIGHT1 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__BETWEEN_RIGHT2 = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_CAST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_COMBINED_LEFT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_COMBINED_RIGHT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__GROUPING_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_CASE_ELSE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_CASE_SIMPLE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_CASE_SEARCH_CONTENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__LIKE_ESCAPE = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_LABELED_DURATION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__NEST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__UPDATE_SOURCE_EXPR_LIST = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__TABLE_FUNCTION = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR_ROW = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__CALL_STATEMENT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Column Definition Regular</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT__COLUMN_DEFINITION_REGULAR = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>XML Table Column Definition Default</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_TABLE_COLUMN_DEFINITION_DEFAULT_FEATURE_COUNT = SQLQueryModelPackage.QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionEncodingImpl <em>XML Serialize Function Encoding</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionEncodingImpl
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLSerializeFunctionEncoding()
     * @generated
     */
    int XML_SERIALIZE_FUNCTION_ENCODING = 52;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_ENCODING__EANNOTATIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_ENCODING__NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_ENCODING__DEPENDENCIES = SQLQueryModelPackage.SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_ENCODING__DESCRIPTION = SQLQueryModelPackage.SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_ENCODING__LABEL = SQLQueryModelPackage.SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_SERIALIZE_FUNCTION_ENCODING__COMMENTS = SQLQueryModelPackage.SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_ENCODING__EXTENSIONS = SQLQueryModelPackage.SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int XML_SERIALIZE_FUNCTION_ENCODING__PRIVILEGES = SQLQueryModelPackage.SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Encoding Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_ENCODING__ENCODING_NAME = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>XML Serialize Function Encoding</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int XML_SERIALIZE_FUNCTION_ENCODING_FEATURE_COUNT = SQLQueryModelPackage.SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType <em>XML Passing Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPassingType()
     * @generated
     */
    int XML_PASSING_TYPE = 53;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType <em>XML Content Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLContentType()
     * @generated
     */
    int XML_CONTENT_TYPE = 54;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType <em>XML Declaration Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLDeclarationType()
     * @generated
     */
    int XML_DECLARATION_TYPE = 55;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType <em>XML Returning Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLReturningType()
     * @generated
     */
    int XML_RETURNING_TYPE = 56;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType <em>XML Null Handling Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLNullHandlingType()
     * @generated
     */
    int XML_NULL_HANDLING_TYPE = 57;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType <em>XML Whitespace Handling Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLWhitespaceHandlingType()
     * @generated
     */
    int XML_WHITESPACE_HANDLING_TYPE = 58;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType <em>XML Empty Handling Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLEmptyHandlingType()
     * @generated
     */
    int XML_EMPTY_HANDLING_TYPE = 59;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2 <em>XML Content Type2</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2
     * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLContentType2()
     * @generated
     */
    int XML_CONTENT_TYPE2 = 60;


	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat <em>XML Value Function Concat</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Concat</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat
     * @generated
     */
    EClass getXMLValueFunctionConcat();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat#getReturningOption <em>Returning Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Returning Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat#getReturningOption()
     * @see #getXMLValueFunctionConcat()
     * @generated
     */
    EAttribute getXMLValueFunctionConcat_ReturningOption();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat#getConcatContentList <em>Concat Content List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Concat Content List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat#getConcatContentList()
     * @see #getXMLValueFunctionConcat()
     * @generated
     */
    EReference getXMLValueFunctionConcat_ConcatContentList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunction <em>XML Value Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunction
     * @generated
     */
    EClass getXMLValueFunction();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationPrefix <em>XML Namespace Declaration Prefix</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Namespace Declaration Prefix</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationPrefix
     * @generated
     */
    EClass getXMLNamespaceDeclarationPrefix();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationPrefix#getPrefix <em>Prefix</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Prefix</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationPrefix#getPrefix()
     * @see #getXMLNamespaceDeclarationPrefix()
     * @generated
     */
    EAttribute getXMLNamespaceDeclarationPrefix_Prefix();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault <em>XML Namespace Declaration Default</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Namespace Declaration Default</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault
     * @generated
     */
    EClass getXMLNamespaceDeclarationDefault();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault#isNoDefault <em>No Default</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>No Default</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationDefault#isNoDefault()
     * @see #getXMLNamespaceDeclarationDefault()
     * @generated
     */
    EAttribute getXMLNamespaceDeclarationDefault_NoDefault();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem <em>XML Attribute Declaration Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Attribute Declaration Item</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem
     * @generated
     */
    EClass getXMLAttributeDeclarationItem();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem#getValueExpr()
     * @see #getXMLAttributeDeclarationItem()
     * @generated
     */
    EReference getXMLAttributeDeclarationItem_ValueExpr();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem#getAttributesDecl <em>Attributes Decl</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Attributes Decl</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributeDeclarationItem#getAttributesDecl()
     * @see #getXMLAttributeDeclarationItem()
     * @generated
     */
    EReference getXMLAttributeDeclarationItem_AttributesDecl();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement <em>XML Value Function Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Element</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement
     * @generated
     */
    EClass getXMLValueFunctionElement();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getElementName <em>Element Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Element Name</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getElementName()
     * @see #getXMLValueFunctionElement()
     * @generated
     */
    EAttribute getXMLValueFunctionElement_ElementName();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getReturningOption <em>Returning Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Returning Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getReturningOption()
     * @see #getXMLValueFunctionElement()
     * @generated
     */
    EAttribute getXMLValueFunctionElement_ReturningOption();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getNamespacesDecl <em>Namespaces Decl</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Namespaces Decl</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getNamespacesDecl()
     * @see #getXMLValueFunctionElement()
     * @generated
     */
    EReference getXMLValueFunctionElement_NamespacesDecl();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getAttributesDecl <em>Attributes Decl</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Attributes Decl</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getAttributesDecl()
     * @see #getXMLValueFunctionElement()
     * @generated
     */
    EReference getXMLValueFunctionElement_AttributesDecl();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getElementContentList <em>Element Content List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Element Content List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElement#getElementContentList()
     * @see #getXMLValueFunctionElement()
     * @generated
     */
    EReference getXMLValueFunctionElement_ElementContentList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem <em>XML Namespace Declaration Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Namespace Declaration Item</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem
     * @generated
     */
    EClass getXMLNamespaceDeclarationItem();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem#getUri <em>Uri</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uri</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem#getUri()
     * @see #getXMLNamespaceDeclarationItem()
     * @generated
     */
    EAttribute getXMLNamespaceDeclarationItem_Uri();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem#getNamespacesDecl <em>Namespaces Decl</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Namespaces Decl</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespaceDeclarationItem#getNamespacesDecl()
     * @see #getXMLNamespaceDeclarationItem()
     * @generated
     */
    EReference getXMLNamespaceDeclarationItem_NamespacesDecl();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem <em>XML Value Function Element Content Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Element Content Item</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem
     * @generated
     */
    EClass getXMLValueFunctionElementContentItem();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem#getValueExpr()
     * @see #getXMLValueFunctionElementContentItem()
     * @generated
     */
    EReference getXMLValueFunctionElementContentItem_ValueExpr();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem#getElementContentList <em>Element Content List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Element Content List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentItem#getElementContentList()
     * @see #getXMLValueFunctionElementContentItem()
     * @generated
     */
    EReference getXMLValueFunctionElementContentItem_ElementContentList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest <em>XML Value Function Forest</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Forest</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest
     * @generated
     */
    EClass getXMLValueFunctionForest();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getNullHandlingOption <em>Null Handling Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Null Handling Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getNullHandlingOption()
     * @see #getXMLValueFunctionForest()
     * @generated
     */
    EAttribute getXMLValueFunctionForest_NullHandlingOption();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getReturningOption <em>Returning Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Returning Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getReturningOption()
     * @see #getXMLValueFunctionForest()
     * @generated
     */
    EAttribute getXMLValueFunctionForest_ReturningOption();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getForestContentList <em>Forest Content List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Forest Content List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getForestContentList()
     * @see #getXMLValueFunctionForest()
     * @generated
     */
    EReference getXMLValueFunctionForest_ForestContentList();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getNamespacesDecl <em>Namespaces Decl</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Namespaces Decl</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForest#getNamespacesDecl()
     * @see #getXMLValueFunctionForest()
     * @generated
     */
    EReference getXMLValueFunctionForest_NamespacesDecl();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment <em>XML Value Function Comment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Comment</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment
     * @generated
     */
    EClass getXMLValueFunctionComment();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment#getReturningOption <em>Returning Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Returning Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment#getReturningOption()
     * @see #getXMLValueFunctionComment()
     * @generated
     */
    EAttribute getXMLValueFunctionComment_ReturningOption();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment#getCommentContent <em>Comment Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Comment Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionComment#getCommentContent()
     * @see #getXMLValueFunctionComment()
     * @generated
     */
    EReference getXMLValueFunctionComment_CommentContent();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument <em>XML Value Function Document</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Document</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument
     * @generated
     */
    EClass getXMLValueFunctionDocument();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument#getReturningOption <em>Returning Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Returning Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument#getReturningOption()
     * @see #getXMLValueFunctionDocument()
     * @generated
     */
    EAttribute getXMLValueFunctionDocument_ReturningOption();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument#getDocumentContent <em>Document Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Document Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocument#getDocumentContent()
     * @see #getXMLValueFunctionDocument()
     * @generated
     */
    EReference getXMLValueFunctionDocument_DocumentContent();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse <em>XML Value Function Parse</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Parse</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse
     * @generated
     */
    EClass getXMLValueFunctionParse();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getContentOption <em>Content Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Content Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getContentOption()
     * @see #getXMLValueFunctionParse()
     * @generated
     */
    EAttribute getXMLValueFunctionParse_ContentOption();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getWhitespaceHandlingOption <em>Whitespace Handling Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Whitespace Handling Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getWhitespaceHandlingOption()
     * @see #getXMLValueFunctionParse()
     * @generated
     */
    EAttribute getXMLValueFunctionParse_WhitespaceHandlingOption();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getParseContent <em>Parse Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Parse Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParse#getParseContent()
     * @see #getXMLValueFunctionParse()
     * @generated
     */
    EReference getXMLValueFunctionParse_ParseContent();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI <em>XML Value Function PI</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function PI</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI
     * @generated
     */
    EClass getXMLValueFunctionPI();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getTargetName <em>Target Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target Name</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getTargetName()
     * @see #getXMLValueFunctionPI()
     * @generated
     */
    EAttribute getXMLValueFunctionPI_TargetName();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getReturningOption <em>Returning Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Returning Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getReturningOption()
     * @see #getXMLValueFunctionPI()
     * @generated
     */
    EAttribute getXMLValueFunctionPI_ReturningOption();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getPIContent <em>PI Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>PI Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPI#getPIContent()
     * @see #getXMLValueFunctionPI()
     * @generated
     */
    EReference getXMLValueFunctionPI_PIContent();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery <em>XML Value Function Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery
     * @generated
     */
    EClass getXMLValueFunctionQuery();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getEmptyHandlingOption <em>Empty Handling Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Empty Handling Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getEmptyHandlingOption()
     * @see #getXMLValueFunctionQuery()
     * @generated
     */
    EAttribute getXMLValueFunctionQuery_EmptyHandlingOption();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryExpr <em>Xquery Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Xquery Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryExpr()
     * @see #getXMLValueFunctionQuery()
     * @generated
     */
    EReference getXMLValueFunctionQuery_XqueryExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryArgList <em>Xquery Arg List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Xquery Arg List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getXqueryArgList()
     * @see #getXMLValueFunctionQuery()
     * @generated
     */
    EReference getXMLValueFunctionQuery_XqueryArgList();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getQueryReturning <em>Query Returning</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query Returning</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery#getQueryReturning()
     * @see #getXMLValueFunctionQuery()
     * @generated
     */
    EReference getXMLValueFunctionQuery_QueryReturning();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText <em>XML Value Function Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Text</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText
     * @generated
     */
    EClass getXMLValueFunctionText();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText#getReturningOption <em>Returning Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Returning Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText#getReturningOption()
     * @see #getXMLValueFunctionText()
     * @generated
     */
    EAttribute getXMLValueFunctionText_ReturningOption();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText#getTextContent <em>Text Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Text Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionText#getTextContent()
     * @see #getXMLValueFunctionText()
     * @generated
     */
    EReference getXMLValueFunctionText_TextContent();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate <em>XML Value Function Validate</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Validate</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate
     * @generated
     */
    EClass getXMLValueFunctionValidate();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getContentOption <em>Content Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Content Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getContentOption()
     * @see #getXMLValueFunctionValidate()
     * @generated
     */
    EAttribute getXMLValueFunctionValidate_ContentOption();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getValidateContent <em>Validate Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Validate Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getValidateContent()
     * @see #getXMLValueFunctionValidate()
     * @generated
     */
    EReference getXMLValueFunctionValidate_ValidateContent();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getValidateAccordingTo <em>Validate According To</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Validate According To</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate#getValidateAccordingTo()
     * @see #getXMLValueFunctionValidate()
     * @generated
     */
    EReference getXMLValueFunctionValidate_ValidateAccordingTo();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast <em>XML Value Expression Cast</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Expression Cast</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast
     * @generated
     */
    EClass getXMLValueExpressionCast();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast#getPassingMechanism <em>Passing Mechanism</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Passing Mechanism</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast#getPassingMechanism()
     * @see #getXMLValueExpressionCast()
     * @generated
     */
    EAttribute getXMLValueExpressionCast_PassingMechanism();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicate <em>XML Predicate</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Predicate</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicate
     * @generated
     */
    EClass getXMLPredicate();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateContent <em>XML Predicate Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Predicate Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateContent
     * @generated
     */
    EClass getXMLPredicateContent();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateDocument <em>XML Predicate Document</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Predicate Document</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateDocument
     * @generated
     */
    EClass getXMLPredicateDocument();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists <em>XML Predicate Exists</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Predicate Exists</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists
     * @generated
     */
    EClass getXMLPredicateExists();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryExpr <em>Xquery Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Xquery Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryExpr()
     * @see #getXMLPredicateExists()
     * @generated
     */
    EReference getXMLPredicateExists_XqueryExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryArgList <em>Xquery Arg List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Xquery Arg List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists#getXqueryArgList()
     * @see #getXMLPredicateExists()
     * @generated
     */
    EReference getXMLPredicateExists_XqueryArgList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateValid <em>XML Predicate Valid</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Predicate Valid</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateValid
     * @generated
     */
    EClass getXMLPredicateValid();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression <em>XML Query Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Query Expression</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression
     * @generated
     */
    EClass getXMLQueryExpression();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getXqueryExprContent <em>Xquery Expr Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Xquery Expr Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getXqueryExprContent()
     * @see #getXMLQueryExpression()
     * @generated
     */
    EAttribute getXMLQueryExpression_XqueryExprContent();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getPredicateExists <em>Predicate Exists</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Predicate Exists</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getPredicateExists()
     * @see #getXMLQueryExpression()
     * @generated
     */
    EReference getXMLQueryExpression_PredicateExists();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getValueFunctionQuery <em>Value Function Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryExpression#getValueFunctionQuery()
     * @see #getXMLQueryExpression()
     * @generated
     */
    EReference getXMLQueryExpression_ValueFunctionQuery();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList <em>XML Query Argument List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Query Argument List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList
     * @generated
     */
    EClass getXMLQueryArgumentList();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getPassingMechanism <em>Passing Mechanism</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Passing Mechanism</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getPassingMechanism()
     * @see #getXMLQueryArgumentList()
     * @generated
     */
    EAttribute getXMLQueryArgumentList_PassingMechanism();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getPredicateExists <em>Predicate Exists</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Predicate Exists</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getPredicateExists()
     * @see #getXMLQueryArgumentList()
     * @generated
     */
    EReference getXMLQueryArgumentList_PredicateExists();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getXqueryArgListChildren <em>Xquery Arg List Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Xquery Arg List Children</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getXqueryArgListChildren()
     * @see #getXMLQueryArgumentList()
     * @generated
     */
    EReference getXMLQueryArgumentList_XqueryArgListChildren();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getValueFunctionQuery <em>Value Function Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getValueFunctionQuery()
     * @see #getXMLQueryArgumentList()
     * @generated
     */
    EReference getXMLQueryArgumentList_ValueFunctionQuery();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getTableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Table Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList#getTableFunction()
     * @see #getXMLQueryArgumentList()
     * @generated
     */
    EReference getXMLQueryArgumentList_TableFunction();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem <em>XML Query Argument Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Query Argument Item</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem
     * @generated
     */
    EClass getXMLQueryArgumentItem();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getPassingMechanism <em>Passing Mechanism</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Passing Mechanism</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getPassingMechanism()
     * @see #getXMLQueryArgumentItem()
     * @generated
     */
    EAttribute getXMLQueryArgumentItem_PassingMechanism();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getXqueryArgList <em>Xquery Arg List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Xquery Arg List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getXqueryArgList()
     * @see #getXMLQueryArgumentItem()
     * @generated
     */
    EReference getXMLQueryArgumentItem_XqueryArgList();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentItem#getValueExpr()
     * @see #getXMLQueryArgumentItem()
     * @generated
     */
    EReference getXMLQueryArgumentItem_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction <em>XML Serialize Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Serialize Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction
     * @generated
     */
    EClass getXMLSerializeFunction();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getContentOption <em>Content Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Content Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getContentOption()
     * @see #getXMLSerializeFunction()
     * @generated
     */
    EAttribute getXMLSerializeFunction_ContentOption();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeVersion <em>Serialize Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Serialize Version</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeVersion()
     * @see #getXMLSerializeFunction()
     * @generated
     */
    EAttribute getXMLSerializeFunction_SerializeVersion();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getDeclarationOption <em>Declaration Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Declaration Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getDeclarationOption()
     * @see #getXMLSerializeFunction()
     * @generated
     */
    EAttribute getXMLSerializeFunction_DeclarationOption();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeTarget <em>Serialize Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Serialize Target</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeTarget()
     * @see #getXMLSerializeFunction()
     * @generated
     */
    EReference getXMLSerializeFunction_SerializeTarget();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeEncoding <em>Serialize Encoding</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Serialize Encoding</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction#getSerializeEncoding()
     * @see #getXMLSerializeFunction()
     * @generated
     */
    EReference getXMLSerializeFunction_SerializeEncoding();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget <em>XML Serialize Function Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Serialize Function Target</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget
     * @generated
     */
    EClass getXMLSerializeFunctionTarget();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget#getSerializeFunction <em>Serialize Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Serialize Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget#getSerializeFunction()
     * @see #getXMLSerializeFunctionTarget()
     * @generated
     */
    EReference getXMLSerializeFunctionTarget_SerializeFunction();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget#getValueExpr()
     * @see #getXMLSerializeFunctionTarget()
     * @generated
     */
    EReference getXMLSerializeFunctionTarget_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction <em>XML Aggregate Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Aggregate Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction
     * @generated
     */
    EClass getXMLAggregateFunction();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction#getReturningOption <em>Returning Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Returning Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction#getReturningOption()
     * @see #getXMLAggregateFunction()
     * @generated
     */
    EAttribute getXMLAggregateFunction_ReturningOption();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction#getSortSpecList <em>Sort Spec List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Sort Spec List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateFunction#getSortSpecList()
     * @see #getXMLAggregateFunction()
     * @generated
     */
    EReference getXMLAggregateFunction_SortSpecList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem <em>XML Value Function Concat Content Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Concat Content Item</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem
     * @generated
     */
    EClass getXMLValueFunctionConcatContentItem();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem#getValueFunctionConcat <em>Value Function Concat</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Concat</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem#getValueFunctionConcat()
     * @see #getXMLValueFunctionConcatContentItem()
     * @generated
     */
    EReference getXMLValueFunctionConcatContentItem_ValueFunctionConcat();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcatContentItem#getValueExpr()
     * @see #getXMLValueFunctionConcatContentItem()
     * @generated
     */
    EReference getXMLValueFunctionConcatContentItem_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent <em>XML Value Function Comment Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Comment Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent
     * @generated
     */
    EClass getXMLValueFunctionCommentContent();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getValueFunctionComment <em>Value Function Comment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Comment</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getValueFunctionComment()
     * @see #getXMLValueFunctionCommentContent()
     * @generated
     */
    EReference getXMLValueFunctionCommentContent_ValueFunctionComment();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionCommentContent#getValueExpr()
     * @see #getXMLValueFunctionCommentContent()
     * @generated
     */
    EReference getXMLValueFunctionCommentContent_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent <em>XML Value Function Document Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Document Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent
     * @generated
     */
    EClass getXMLValueFunctionDocumentContent();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent#getValueFunctionDocument <em>Value Function Document</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Document</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent#getValueFunctionDocument()
     * @see #getXMLValueFunctionDocumentContent()
     * @generated
     */
    EReference getXMLValueFunctionDocumentContent_ValueFunctionDocument();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionDocumentContent#getValueExpr()
     * @see #getXMLValueFunctionDocumentContent()
     * @generated
     */
    EReference getXMLValueFunctionDocumentContent_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification <em>XML Aggregate Sort Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Aggregate Sort Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification
     * @generated
     */
    EClass getXMLAggregateSortSpecification();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification#getAggregateFunction <em>Aggregate Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Aggregate Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification#getAggregateFunction()
     * @see #getXMLAggregateSortSpecification()
     * @generated
     */
    EReference getXMLAggregateSortSpecification_AggregateFunction();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification#getOrderBySpec <em>Order By Spec</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Order By Spec</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAggregateSortSpecification#getOrderBySpec()
     * @see #getXMLAggregateSortSpecification()
     * @generated
     */
    EReference getXMLAggregateSortSpecification_OrderBySpec();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem <em>XML Value Function Forest Content Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Forest Content Item</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem
     * @generated
     */
    EClass getXMLValueFunctionForestContentItem();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem#getValueFunctionForest <em>Value Function Forest</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Forest</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem#getValueFunctionForest()
     * @see #getXMLValueFunctionForestContentItem()
     * @generated
     */
    EReference getXMLValueFunctionForestContentItem_ValueFunctionForest();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionForestContentItem#getValueExpr()
     * @see #getXMLValueFunctionForestContentItem()
     * @generated
     */
    EReference getXMLValueFunctionForestContentItem_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent <em>XML Value Function Parse Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Parse Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent
     * @generated
     */
    EClass getXMLValueFunctionParseContent();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent#getValueFunctionParse <em>Value Function Parse</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Parse</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent#getValueFunctionParse()
     * @see #getXMLValueFunctionParseContent()
     * @generated
     */
    EReference getXMLValueFunctionParseContent_ValueFunctionParse();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionParseContent#getValueExpr()
     * @see #getXMLValueFunctionParseContent()
     * @generated
     */
    EReference getXMLValueFunctionParseContent_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent <em>XML Value Function PI Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function PI Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent
     * @generated
     */
    EClass getXMLValueFunctionPIContent();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent#getValueFunctionPI <em>Value Function PI</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function PI</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent#getValueFunctionPI()
     * @see #getXMLValueFunctionPIContent()
     * @generated
     */
    EReference getXMLValueFunctionPIContent_ValueFunctionPI();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionPIContent#getValueExpr()
     * @see #getXMLValueFunctionPIContent()
     * @generated
     */
    EReference getXMLValueFunctionPIContent_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction <em>XML Table Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Table Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction
     * @generated
     */
    EClass getXMLTableFunction();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getTableRowPattern <em>Table Row Pattern</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Table Row Pattern</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getTableRowPattern()
     * @see #getXMLTableFunction()
     * @generated
     */
    EAttribute getXMLTableFunction_TableRowPattern();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getXqueryArgList <em>Xquery Arg List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Xquery Arg List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getXqueryArgList()
     * @see #getXMLTableFunction()
     * @generated
     */
    EReference getXMLTableFunction_XqueryArgList();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getColumnDefList <em>Column Def List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Column Def List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getColumnDefList()
     * @see #getXMLTableFunction()
     * @generated
     */
    EReference getXMLTableFunction_ColumnDefList();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getNamespacesDecl <em>Namespaces Decl</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Namespaces Decl</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction#getNamespacesDecl()
     * @see #getXMLTableFunction()
     * @generated
     */
    EReference getXMLTableFunction_NamespacesDecl();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent <em>XML Value Function Text Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Text Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent
     * @generated
     */
    EClass getXMLValueFunctionTextContent();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent#getValueFunctionText <em>Value Function Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Text</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent#getValueFunctionText()
     * @see #getXMLValueFunctionTextContent()
     * @generated
     */
    EReference getXMLValueFunctionTextContent_ValueFunctionText();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionTextContent#getValueExpr()
     * @see #getXMLValueFunctionTextContent()
     * @generated
     */
    EReference getXMLValueFunctionTextContent_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent <em>XML Value Function Validate Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Validate Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent
     * @generated
     */
    EClass getXMLValueFunctionValidateContent();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent#getValueFunctionValidate <em>Value Function Validate</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Validate</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent#getValueFunctionValidate()
     * @see #getXMLValueFunctionValidateContent()
     * @generated
     */
    EReference getXMLValueFunctionValidateContent_ValueFunctionValidate();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateContent#getValueExpr()
     * @see #getXMLValueFunctionValidateContent()
     * @generated
     */
    EReference getXMLValueFunctionValidateContent_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem <em>XML Table Column Definition Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Table Column Definition Item</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem
     * @generated
     */
    EClass getXMLTableColumnDefinitionItem();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem#getTableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Table Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem#getTableFunction()
     * @see #getXMLTableColumnDefinitionItem()
     * @generated
     */
    EReference getXMLTableColumnDefinitionItem_TableFunction();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular <em>XML Table Column Definition Regular</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Table Column Definition Regular</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular
     * @generated
     */
    EClass getXMLTableColumnDefinitionRegular();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getDataType <em>Data Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Data Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getDataType()
     * @see #getXMLTableColumnDefinitionRegular()
     * @generated
     */
    EReference getXMLTableColumnDefinitionRegular_DataType();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getPassingOption <em>Passing Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Passing Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getPassingOption()
     * @see #getXMLTableColumnDefinitionRegular()
     * @generated
     */
    EAttribute getXMLTableColumnDefinitionRegular_PassingOption();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getTableColumnPattern <em>Table Column Pattern</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Table Column Pattern</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getTableColumnPattern()
     * @see #getXMLTableColumnDefinitionRegular()
     * @generated
     */
    EAttribute getXMLTableColumnDefinitionRegular_TableColumnPattern();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getColumnDefinitionDefault <em>Column Definition Default</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Column Definition Default</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular#getColumnDefinitionDefault()
     * @see #getXMLTableColumnDefinitionRegular()
     * @generated
     */
    EReference getXMLTableColumnDefinitionRegular_ColumnDefinitionDefault();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionOrdinality <em>XML Table Column Definition Ordinality</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Table Column Definition Ordinality</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionOrdinality
     * @generated
     */
    EClass getXMLTableColumnDefinitionOrdinality();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo <em>XML Value Function Validate According To</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Validate According To</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo
     * @generated
     */
    EClass getXMLValueFunctionValidateAccordingTo();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValueFunctionValidate <em>Value Function Validate</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Validate</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValueFunctionValidate()
     * @see #getXMLValueFunctionValidateAccordingTo()
     * @generated
     */
    EReference getXMLValueFunctionValidateAccordingTo_ValueFunctionValidate();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValidateElement <em>Validate Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Validate Element</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingTo#getValidateElement()
     * @see #getXMLValueFunctionValidateAccordingTo()
     * @generated
     */
    EReference getXMLValueFunctionValidateAccordingTo_ValidateElement();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI <em>XML Value Function Validate According To URI</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Validate According To URI</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI
     * @generated
     */
    EClass getXMLValueFunctionValidateAccordingToURI();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#isNoNamespace <em>No Namespace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>No Namespace</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#isNoNamespace()
     * @see #getXMLValueFunctionValidateAccordingToURI()
     * @generated
     */
    EAttribute getXMLValueFunctionValidateAccordingToURI_NoNamespace();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#getTargetNamespaceURI <em>Target Namespace URI</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target Namespace URI</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#getTargetNamespaceURI()
     * @see #getXMLValueFunctionValidateAccordingToURI()
     * @generated
     */
    EAttribute getXMLValueFunctionValidateAccordingToURI_TargetNamespaceURI();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#getSchemaLocationURI <em>Schema Location URI</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Schema Location URI</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI#getSchemaLocationURI()
     * @see #getXMLValueFunctionValidateAccordingToURI()
     * @generated
     */
    EAttribute getXMLValueFunctionValidateAccordingToURI_SchemaLocationURI();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier <em>XML Value Function Validate According To Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Validate According To Identifier</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier
     * @generated
     */
    EClass getXMLValueFunctionValidateAccordingToIdentifier();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier#getSchemaName <em>Schema Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Schema Name</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier#getSchemaName()
     * @see #getXMLValueFunctionValidateAccordingToIdentifier()
     * @generated
     */
    EAttribute getXMLValueFunctionValidateAccordingToIdentifier_SchemaName();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier#getRegisteredXMLSchemaName <em>Registered XML Schema Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Registered XML Schema Name</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier#getRegisteredXMLSchemaName()
     * @see #getXMLValueFunctionValidateAccordingToIdentifier()
     * @generated
     */
    EAttribute getXMLValueFunctionValidateAccordingToIdentifier_RegisteredXMLSchemaName();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName <em>XML Value Function Validate Element Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Validate Element Name</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName
     * @generated
     */
    EClass getXMLValueFunctionValidateElementName();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName#getValidateElement <em>Validate Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Validate Element</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementName#getValidateElement()
     * @see #getXMLValueFunctionValidateElementName()
     * @generated
     */
    EReference getXMLValueFunctionValidateElementName_ValidateElement();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace <em>XML Value Function Validate Element Namespace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Validate Element Namespace</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace
     * @generated
     */
    EClass getXMLValueFunctionValidateElementNamespace();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#isNoNamespace <em>No Namespace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>No Namespace</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#isNoNamespace()
     * @see #getXMLValueFunctionValidateElementNamespace()
     * @generated
     */
    EAttribute getXMLValueFunctionValidateElementNamespace_NoNamespace();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#getNamespaceURI <em>Namespace URI</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Namespace URI</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#getNamespaceURI()
     * @see #getXMLValueFunctionValidateElementNamespace()
     * @generated
     */
    EAttribute getXMLValueFunctionValidateElementNamespace_NamespaceURI();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#getValidateElement <em>Validate Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Validate Element</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElementNamespace#getValidateElement()
     * @see #getXMLValueFunctionValidateElementNamespace()
     * @generated
     */
    EReference getXMLValueFunctionValidateElementNamespace_ValidateElement();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration <em>XML Namespaces Declaration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Namespaces Declaration</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration
     * @generated
     */
    EClass getXMLNamespacesDeclaration();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getNamespaceDecltemList <em>Namespace Decltem List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Namespace Decltem List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getNamespaceDecltemList()
     * @see #getXMLNamespacesDeclaration()
     * @generated
     */
    EReference getXMLNamespacesDeclaration_NamespaceDecltemList();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionElement <em>Value Function Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Element</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionElement()
     * @see #getXMLNamespacesDeclaration()
     * @generated
     */
    EReference getXMLNamespacesDeclaration_ValueFunctionElement();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionForest <em>Value Function Forest</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Forest</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getValueFunctionForest()
     * @see #getXMLNamespacesDeclaration()
     * @generated
     */
    EReference getXMLNamespacesDeclaration_ValueFunctionForest();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getTableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Table Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration#getTableFunction()
     * @see #getXMLNamespacesDeclaration()
     * @generated
     */
    EReference getXMLNamespacesDeclaration_TableFunction();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration <em>XML Attributes Declaration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Attributes Declaration</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration
     * @generated
     */
    EClass getXMLAttributesDeclaration();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getValueFunctionElement <em>Value Function Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Element</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getValueFunctionElement()
     * @see #getXMLAttributesDeclaration()
     * @generated
     */
    EReference getXMLAttributesDeclaration_ValueFunctionElement();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getAttributeDeclItem <em>Attribute Decl Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Attribute Decl Item</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLAttributesDeclaration#getAttributeDeclItem()
     * @see #getXMLAttributesDeclaration()
     * @generated
     */
    EReference getXMLAttributesDeclaration_AttributeDeclItem();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList <em>XML Value Function Element Content List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Element Content List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList
     * @generated
     */
    EClass getXMLValueFunctionElementContentList();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getNullHandlingOption <em>Null Handling Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Null Handling Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getNullHandlingOption()
     * @see #getXMLValueFunctionElementContentList()
     * @generated
     */
    EAttribute getXMLValueFunctionElementContentList_NullHandlingOption();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getValueFunctionElement <em>Value Function Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Element</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getValueFunctionElement()
     * @see #getXMLValueFunctionElementContentList()
     * @generated
     */
    EReference getXMLValueFunctionElementContentList_ValueFunctionElement();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getElementContentListChildren <em>Element Content List Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Element Content List Children</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionElementContentList#getElementContentListChildren()
     * @see #getXMLValueFunctionElementContentList()
     * @generated
     */
    EReference getXMLValueFunctionElementContentList_ElementContentListChildren();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning <em>XML Value Function Query Returning</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Query Returning</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning
     * @generated
     */
    EClass getXMLValueFunctionQueryReturning();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getReturningOption <em>Returning Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Returning Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getReturningOption()
     * @see #getXMLValueFunctionQueryReturning()
     * @generated
     */
    EAttribute getXMLValueFunctionQueryReturning_ReturningOption();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getPassingOption <em>Passing Option</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Passing Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getPassingOption()
     * @see #getXMLValueFunctionQueryReturning()
     * @generated
     */
    EAttribute getXMLValueFunctionQueryReturning_PassingOption();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getValueFunctionQuery <em>Value Function Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Function Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning#getValueFunctionQuery()
     * @see #getXMLValueFunctionQueryReturning()
     * @generated
     */
    EReference getXMLValueFunctionQueryReturning_ValueFunctionQuery();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement <em>XML Value Function Validate Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Value Function Validate Element</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement
     * @generated
     */
    EClass getXMLValueFunctionValidateElement();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementNamespace <em>Validate Element Namespace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Validate Element Namespace</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementNamespace()
     * @see #getXMLValueFunctionValidateElement()
     * @generated
     */
    EReference getXMLValueFunctionValidateElement_ValidateElementNamespace();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementName <em>Validate Element Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Validate Element Name</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateElementName()
     * @see #getXMLValueFunctionValidateElement()
     * @generated
     */
    EReference getXMLValueFunctionValidateElement_ValidateElementName();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateAccordingTo <em>Validate According To</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Validate According To</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement#getValidateAccordingTo()
     * @see #getXMLValueFunctionValidateElement()
     * @generated
     */
    EReference getXMLValueFunctionValidateElement_ValidateAccordingTo();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault <em>XML Table Column Definition Default</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Table Column Definition Default</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault
     * @generated
     */
    EClass getXMLTableColumnDefinitionDefault();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getValueExpr()
     * @see #getXMLTableColumnDefinitionDefault()
     * @generated
     */
    EReference getXMLTableColumnDefinitionDefault_ValueExpr();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getColumnDefinitionRegular <em>Column Definition Regular</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Column Definition Regular</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault#getColumnDefinitionRegular()
     * @see #getXMLTableColumnDefinitionDefault()
     * @generated
     */
    EReference getXMLTableColumnDefinitionDefault_ColumnDefinitionRegular();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding <em>XML Serialize Function Encoding</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>XML Serialize Function Encoding</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding
     * @generated
     */
    EClass getXMLSerializeFunctionEncoding();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding#getEncodingName <em>Encoding Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Encoding Name</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding#getEncodingName()
     * @see #getXMLSerializeFunctionEncoding()
     * @generated
     */
    EAttribute getXMLSerializeFunctionEncoding_EncodingName();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType <em>XML Passing Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>XML Passing Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
     * @generated
     */
    EEnum getXMLPassingType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType <em>XML Content Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>XML Content Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType
     * @generated
     */
    EEnum getXMLContentType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType <em>XML Declaration Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>XML Declaration Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType
     * @generated
     */
    EEnum getXMLDeclarationType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType <em>XML Returning Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>XML Returning Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType
     * @generated
     */
    EEnum getXMLReturningType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType <em>XML Null Handling Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>XML Null Handling Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType
     * @generated
     */
    EEnum getXMLNullHandlingType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType <em>XML Whitespace Handling Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>XML Whitespace Handling Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType
     * @generated
     */
    EEnum getXMLWhitespaceHandlingType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType <em>XML Empty Handling Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>XML Empty Handling Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType
     * @generated
     */
    EEnum getXMLEmptyHandlingType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2 <em>XML Content Type2</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>XML Content Type2</em>'.
     * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2
     * @generated
     */
    EEnum getXMLContentType2();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	SQLXMLQueryModelFactory getSQLXMLQueryModelFactory();

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
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionConcatImpl <em>XML Value Function Concat</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionConcatImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionConcat()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_CONCAT = eINSTANCE.getXMLValueFunctionConcat();

		/**
         * The meta object literal for the '<em><b>Returning Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_CONCAT__RETURNING_OPTION = eINSTANCE.getXMLValueFunctionConcat_ReturningOption();

		/**
         * The meta object literal for the '<em><b>Concat Content List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_CONCAT__CONCAT_CONTENT_LIST = eINSTANCE.getXMLValueFunctionConcat_ConcatContentList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionImpl <em>XML Value Function</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunction()
         * @generated
         */
		EClass XML_VALUE_FUNCTION = eINSTANCE.getXMLValueFunction();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationPrefixImpl <em>XML Namespace Declaration Prefix</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationPrefixImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLNamespaceDeclarationPrefix()
         * @generated
         */
		EClass XML_NAMESPACE_DECLARATION_PREFIX = eINSTANCE.getXMLNamespaceDeclarationPrefix();

		/**
         * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_NAMESPACE_DECLARATION_PREFIX__PREFIX = eINSTANCE.getXMLNamespaceDeclarationPrefix_Prefix();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationDefaultImpl <em>XML Namespace Declaration Default</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationDefaultImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLNamespaceDeclarationDefault()
         * @generated
         */
		EClass XML_NAMESPACE_DECLARATION_DEFAULT = eINSTANCE.getXMLNamespaceDeclarationDefault();

		/**
         * The meta object literal for the '<em><b>No Default</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_NAMESPACE_DECLARATION_DEFAULT__NO_DEFAULT = eINSTANCE.getXMLNamespaceDeclarationDefault_NoDefault();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributeDeclarationItemImpl <em>XML Attribute Declaration Item</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributeDeclarationItemImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLAttributeDeclarationItem()
         * @generated
         */
		EClass XML_ATTRIBUTE_DECLARATION_ITEM = eINSTANCE.getXMLAttributeDeclarationItem();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_ATTRIBUTE_DECLARATION_ITEM__VALUE_EXPR = eINSTANCE.getXMLAttributeDeclarationItem_ValueExpr();

		/**
         * The meta object literal for the '<em><b>Attributes Decl</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_ATTRIBUTE_DECLARATION_ITEM__ATTRIBUTES_DECL = eINSTANCE.getXMLAttributeDeclarationItem_AttributesDecl();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementImpl <em>XML Value Function Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionElement()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_ELEMENT = eINSTANCE.getXMLValueFunctionElement();

		/**
         * The meta object literal for the '<em><b>Element Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_ELEMENT__ELEMENT_NAME = eINSTANCE.getXMLValueFunctionElement_ElementName();

		/**
         * The meta object literal for the '<em><b>Returning Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_ELEMENT__RETURNING_OPTION = eINSTANCE.getXMLValueFunctionElement_ReturningOption();

		/**
         * The meta object literal for the '<em><b>Namespaces Decl</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_ELEMENT__NAMESPACES_DECL = eINSTANCE.getXMLValueFunctionElement_NamespacesDecl();

		/**
         * The meta object literal for the '<em><b>Attributes Decl</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_ELEMENT__ATTRIBUTES_DECL = eINSTANCE.getXMLValueFunctionElement_AttributesDecl();

		/**
         * The meta object literal for the '<em><b>Element Content List</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_ELEMENT__ELEMENT_CONTENT_LIST = eINSTANCE.getXMLValueFunctionElement_ElementContentList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationItemImpl <em>XML Namespace Declaration Item</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespaceDeclarationItemImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLNamespaceDeclarationItem()
         * @generated
         */
		EClass XML_NAMESPACE_DECLARATION_ITEM = eINSTANCE.getXMLNamespaceDeclarationItem();

		/**
         * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_NAMESPACE_DECLARATION_ITEM__URI = eINSTANCE.getXMLNamespaceDeclarationItem_Uri();

		/**
         * The meta object literal for the '<em><b>Namespaces Decl</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_NAMESPACE_DECLARATION_ITEM__NAMESPACES_DECL = eINSTANCE.getXMLNamespaceDeclarationItem_NamespacesDecl();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentItemImpl <em>XML Value Function Element Content Item</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentItemImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionElementContentItem()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM = eINSTANCE.getXMLValueFunctionElementContentItem();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__VALUE_EXPR = eINSTANCE.getXMLValueFunctionElementContentItem_ValueExpr();

		/**
         * The meta object literal for the '<em><b>Element Content List</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_ELEMENT_CONTENT_ITEM__ELEMENT_CONTENT_LIST = eINSTANCE.getXMLValueFunctionElementContentItem_ElementContentList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestImpl <em>XML Value Function Forest</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionForest()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_FOREST = eINSTANCE.getXMLValueFunctionForest();

		/**
         * The meta object literal for the '<em><b>Null Handling Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_FOREST__NULL_HANDLING_OPTION = eINSTANCE.getXMLValueFunctionForest_NullHandlingOption();

		/**
         * The meta object literal for the '<em><b>Returning Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_FOREST__RETURNING_OPTION = eINSTANCE.getXMLValueFunctionForest_ReturningOption();

		/**
         * The meta object literal for the '<em><b>Forest Content List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_FOREST__FOREST_CONTENT_LIST = eINSTANCE.getXMLValueFunctionForest_ForestContentList();

		/**
         * The meta object literal for the '<em><b>Namespaces Decl</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_FOREST__NAMESPACES_DECL = eINSTANCE.getXMLValueFunctionForest_NamespacesDecl();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionCommentImpl <em>XML Value Function Comment</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionCommentImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionComment()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_COMMENT = eINSTANCE.getXMLValueFunctionComment();

		/**
         * The meta object literal for the '<em><b>Returning Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_COMMENT__RETURNING_OPTION = eINSTANCE.getXMLValueFunctionComment_ReturningOption();

		/**
         * The meta object literal for the '<em><b>Comment Content</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_COMMENT__COMMENT_CONTENT = eINSTANCE.getXMLValueFunctionComment_CommentContent();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionDocumentImpl <em>XML Value Function Document</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionDocumentImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionDocument()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_DOCUMENT = eINSTANCE.getXMLValueFunctionDocument();

		/**
         * The meta object literal for the '<em><b>Returning Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_DOCUMENT__RETURNING_OPTION = eINSTANCE.getXMLValueFunctionDocument_ReturningOption();

		/**
         * The meta object literal for the '<em><b>Document Content</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_DOCUMENT__DOCUMENT_CONTENT = eINSTANCE.getXMLValueFunctionDocument_DocumentContent();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseImpl <em>XML Value Function Parse</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionParse()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_PARSE = eINSTANCE.getXMLValueFunctionParse();

		/**
         * The meta object literal for the '<em><b>Content Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_PARSE__CONTENT_OPTION = eINSTANCE.getXMLValueFunctionParse_ContentOption();

		/**
         * The meta object literal for the '<em><b>Whitespace Handling Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_PARSE__WHITESPACE_HANDLING_OPTION = eINSTANCE.getXMLValueFunctionParse_WhitespaceHandlingOption();

		/**
         * The meta object literal for the '<em><b>Parse Content</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_PARSE__PARSE_CONTENT = eINSTANCE.getXMLValueFunctionParse_ParseContent();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionPIImpl <em>XML Value Function PI</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionPIImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionPI()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_PI = eINSTANCE.getXMLValueFunctionPI();

		/**
         * The meta object literal for the '<em><b>Target Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_PI__TARGET_NAME = eINSTANCE.getXMLValueFunctionPI_TargetName();

		/**
         * The meta object literal for the '<em><b>Returning Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_PI__RETURNING_OPTION = eINSTANCE.getXMLValueFunctionPI_ReturningOption();

		/**
         * The meta object literal for the '<em><b>PI Content</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_PI__PI_CONTENT = eINSTANCE.getXMLValueFunctionPI_PIContent();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryImpl <em>XML Value Function Query</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionQuery()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_QUERY = eINSTANCE.getXMLValueFunctionQuery();

		/**
         * The meta object literal for the '<em><b>Empty Handling Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_QUERY__EMPTY_HANDLING_OPTION = eINSTANCE.getXMLValueFunctionQuery_EmptyHandlingOption();

		/**
         * The meta object literal for the '<em><b>Xquery Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_QUERY__XQUERY_EXPR = eINSTANCE.getXMLValueFunctionQuery_XqueryExpr();

		/**
         * The meta object literal for the '<em><b>Xquery Arg List</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_QUERY__XQUERY_ARG_LIST = eINSTANCE.getXMLValueFunctionQuery_XqueryArgList();

		/**
         * The meta object literal for the '<em><b>Query Returning</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING = eINSTANCE.getXMLValueFunctionQuery_QueryReturning();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionTextImpl <em>XML Value Function Text</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionTextImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionText()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_TEXT = eINSTANCE.getXMLValueFunctionText();

		/**
         * The meta object literal for the '<em><b>Returning Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_TEXT__RETURNING_OPTION = eINSTANCE.getXMLValueFunctionText_ReturningOption();

		/**
         * The meta object literal for the '<em><b>Text Content</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_TEXT__TEXT_CONTENT = eINSTANCE.getXMLValueFunctionText_TextContent();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateImpl <em>XML Value Function Validate</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidate()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_VALIDATE = eINSTANCE.getXMLValueFunctionValidate();

		/**
         * The meta object literal for the '<em><b>Content Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_VALIDATE__CONTENT_OPTION = eINSTANCE.getXMLValueFunctionValidate_ContentOption();

		/**
         * The meta object literal for the '<em><b>Validate Content</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE__VALIDATE_CONTENT = eINSTANCE.getXMLValueFunctionValidate_ValidateContent();

		/**
         * The meta object literal for the '<em><b>Validate According To</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE__VALIDATE_ACCORDING_TO = eINSTANCE.getXMLValueFunctionValidate_ValidateAccordingTo();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueExpressionCastImpl <em>XML Value Expression Cast</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueExpressionCastImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueExpressionCast()
         * @generated
         */
		EClass XML_VALUE_EXPRESSION_CAST = eINSTANCE.getXMLValueExpressionCast();

		/**
         * The meta object literal for the '<em><b>Passing Mechanism</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_EXPRESSION_CAST__PASSING_MECHANISM = eINSTANCE.getXMLValueExpressionCast_PassingMechanism();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateImpl <em>XML Predicate</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPredicate()
         * @generated
         */
		EClass XML_PREDICATE = eINSTANCE.getXMLPredicate();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateContentImpl <em>XML Predicate Content</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateContentImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPredicateContent()
         * @generated
         */
		EClass XML_PREDICATE_CONTENT = eINSTANCE.getXMLPredicateContent();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateDocumentImpl <em>XML Predicate Document</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateDocumentImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPredicateDocument()
         * @generated
         */
		EClass XML_PREDICATE_DOCUMENT = eINSTANCE.getXMLPredicateDocument();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateExistsImpl <em>XML Predicate Exists</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateExistsImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPredicateExists()
         * @generated
         */
		EClass XML_PREDICATE_EXISTS = eINSTANCE.getXMLPredicateExists();

		/**
         * The meta object literal for the '<em><b>Xquery Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_PREDICATE_EXISTS__XQUERY_EXPR = eINSTANCE.getXMLPredicateExists_XqueryExpr();

		/**
         * The meta object literal for the '<em><b>Xquery Arg List</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_PREDICATE_EXISTS__XQUERY_ARG_LIST = eINSTANCE.getXMLPredicateExists_XqueryArgList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateValidImpl <em>XML Predicate Valid</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLPredicateValidImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPredicateValid()
         * @generated
         */
		EClass XML_PREDICATE_VALID = eINSTANCE.getXMLPredicateValid();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryExpressionImpl <em>XML Query Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryExpressionImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLQueryExpression()
         * @generated
         */
		EClass XML_QUERY_EXPRESSION = eINSTANCE.getXMLQueryExpression();

		/**
         * The meta object literal for the '<em><b>Xquery Expr Content</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_QUERY_EXPRESSION__XQUERY_EXPR_CONTENT = eINSTANCE.getXMLQueryExpression_XqueryExprContent();

		/**
         * The meta object literal for the '<em><b>Predicate Exists</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_QUERY_EXPRESSION__PREDICATE_EXISTS = eINSTANCE.getXMLQueryExpression_PredicateExists();

		/**
         * The meta object literal for the '<em><b>Value Function Query</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_QUERY_EXPRESSION__VALUE_FUNCTION_QUERY = eINSTANCE.getXMLQueryExpression_ValueFunctionQuery();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl <em>XML Query Argument List</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentListImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLQueryArgumentList()
         * @generated
         */
		EClass XML_QUERY_ARGUMENT_LIST = eINSTANCE.getXMLQueryArgumentList();

		/**
         * The meta object literal for the '<em><b>Passing Mechanism</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_QUERY_ARGUMENT_LIST__PASSING_MECHANISM = eINSTANCE.getXMLQueryArgumentList_PassingMechanism();

		/**
         * The meta object literal for the '<em><b>Predicate Exists</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_QUERY_ARGUMENT_LIST__PREDICATE_EXISTS = eINSTANCE.getXMLQueryArgumentList_PredicateExists();

		/**
         * The meta object literal for the '<em><b>Xquery Arg List Children</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_QUERY_ARGUMENT_LIST__XQUERY_ARG_LIST_CHILDREN = eINSTANCE.getXMLQueryArgumentList_XqueryArgListChildren();

		/**
         * The meta object literal for the '<em><b>Value Function Query</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_QUERY_ARGUMENT_LIST__VALUE_FUNCTION_QUERY = eINSTANCE.getXMLQueryArgumentList_ValueFunctionQuery();

		/**
         * The meta object literal for the '<em><b>Table Function</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION = eINSTANCE.getXMLQueryArgumentList_TableFunction();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentItemImpl <em>XML Query Argument Item</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLQueryArgumentItemImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLQueryArgumentItem()
         * @generated
         */
		EClass XML_QUERY_ARGUMENT_ITEM = eINSTANCE.getXMLQueryArgumentItem();

		/**
         * The meta object literal for the '<em><b>Passing Mechanism</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_QUERY_ARGUMENT_ITEM__PASSING_MECHANISM = eINSTANCE.getXMLQueryArgumentItem_PassingMechanism();

		/**
         * The meta object literal for the '<em><b>Xquery Arg List</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_QUERY_ARGUMENT_ITEM__XQUERY_ARG_LIST = eINSTANCE.getXMLQueryArgumentItem_XqueryArgList();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_QUERY_ARGUMENT_ITEM__VALUE_EXPR = eINSTANCE.getXMLQueryArgumentItem_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionImpl <em>XML Serialize Function</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLSerializeFunction()
         * @generated
         */
		EClass XML_SERIALIZE_FUNCTION = eINSTANCE.getXMLSerializeFunction();

		/**
         * The meta object literal for the '<em><b>Content Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_SERIALIZE_FUNCTION__CONTENT_OPTION = eINSTANCE.getXMLSerializeFunction_ContentOption();

		/**
         * The meta object literal for the '<em><b>Serialize Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION = eINSTANCE.getXMLSerializeFunction_SerializeVersion();

		/**
         * The meta object literal for the '<em><b>Declaration Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_SERIALIZE_FUNCTION__DECLARATION_OPTION = eINSTANCE.getXMLSerializeFunction_DeclarationOption();

		/**
         * The meta object literal for the '<em><b>Serialize Target</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET = eINSTANCE.getXMLSerializeFunction_SerializeTarget();

		/**
         * The meta object literal for the '<em><b>Serialize Encoding</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING = eINSTANCE.getXMLSerializeFunction_SerializeEncoding();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionTargetImpl <em>XML Serialize Function Target</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionTargetImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLSerializeFunctionTarget()
         * @generated
         */
		EClass XML_SERIALIZE_FUNCTION_TARGET = eINSTANCE.getXMLSerializeFunctionTarget();

		/**
         * The meta object literal for the '<em><b>Serialize Function</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_SERIALIZE_FUNCTION_TARGET__SERIALIZE_FUNCTION = eINSTANCE.getXMLSerializeFunctionTarget_SerializeFunction();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_SERIALIZE_FUNCTION_TARGET__VALUE_EXPR = eINSTANCE.getXMLSerializeFunctionTarget_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAggregateFunctionImpl <em>XML Aggregate Function</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAggregateFunctionImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLAggregateFunction()
         * @generated
         */
		EClass XML_AGGREGATE_FUNCTION = eINSTANCE.getXMLAggregateFunction();

		/**
         * The meta object literal for the '<em><b>Returning Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_AGGREGATE_FUNCTION__RETURNING_OPTION = eINSTANCE.getXMLAggregateFunction_ReturningOption();

		/**
         * The meta object literal for the '<em><b>Sort Spec List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_AGGREGATE_FUNCTION__SORT_SPEC_LIST = eINSTANCE.getXMLAggregateFunction_SortSpecList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionConcatContentItemImpl <em>XML Value Function Concat Content Item</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionConcatContentItemImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionConcatContentItem()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM = eINSTANCE.getXMLValueFunctionConcatContentItem();

		/**
         * The meta object literal for the '<em><b>Value Function Concat</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_FUNCTION_CONCAT = eINSTANCE.getXMLValueFunctionConcatContentItem_ValueFunctionConcat();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_CONCAT_CONTENT_ITEM__VALUE_EXPR = eINSTANCE.getXMLValueFunctionConcatContentItem_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionCommentContentImpl <em>XML Value Function Comment Content</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionCommentContentImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionCommentContent()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_COMMENT_CONTENT = eINSTANCE.getXMLValueFunctionCommentContent();

		/**
         * The meta object literal for the '<em><b>Value Function Comment</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_FUNCTION_COMMENT = eINSTANCE.getXMLValueFunctionCommentContent_ValueFunctionComment();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_COMMENT_CONTENT__VALUE_EXPR = eINSTANCE.getXMLValueFunctionCommentContent_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionDocumentContentImpl <em>XML Value Function Document Content</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionDocumentContentImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionDocumentContent()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_DOCUMENT_CONTENT = eINSTANCE.getXMLValueFunctionDocumentContent();

		/**
         * The meta object literal for the '<em><b>Value Function Document</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_FUNCTION_DOCUMENT = eINSTANCE.getXMLValueFunctionDocumentContent_ValueFunctionDocument();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_DOCUMENT_CONTENT__VALUE_EXPR = eINSTANCE.getXMLValueFunctionDocumentContent_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAggregateSortSpecificationImpl <em>XML Aggregate Sort Specification</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAggregateSortSpecificationImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLAggregateSortSpecification()
         * @generated
         */
		EClass XML_AGGREGATE_SORT_SPECIFICATION = eINSTANCE.getXMLAggregateSortSpecification();

		/**
         * The meta object literal for the '<em><b>Aggregate Function</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_AGGREGATE_SORT_SPECIFICATION__AGGREGATE_FUNCTION = eINSTANCE.getXMLAggregateSortSpecification_AggregateFunction();

		/**
         * The meta object literal for the '<em><b>Order By Spec</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_AGGREGATE_SORT_SPECIFICATION__ORDER_BY_SPEC = eINSTANCE.getXMLAggregateSortSpecification_OrderBySpec();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestContentItemImpl <em>XML Value Function Forest Content Item</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionForestContentItemImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionForestContentItem()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM = eINSTANCE.getXMLValueFunctionForestContentItem();

		/**
         * The meta object literal for the '<em><b>Value Function Forest</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_FUNCTION_FOREST = eINSTANCE.getXMLValueFunctionForestContentItem_ValueFunctionForest();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_FOREST_CONTENT_ITEM__VALUE_EXPR = eINSTANCE.getXMLValueFunctionForestContentItem_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseContentImpl <em>XML Value Function Parse Content</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionParseContentImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionParseContent()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_PARSE_CONTENT = eINSTANCE.getXMLValueFunctionParseContent();

		/**
         * The meta object literal for the '<em><b>Value Function Parse</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_FUNCTION_PARSE = eINSTANCE.getXMLValueFunctionParseContent_ValueFunctionParse();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_PARSE_CONTENT__VALUE_EXPR = eINSTANCE.getXMLValueFunctionParseContent_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionPIContentImpl <em>XML Value Function PI Content</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionPIContentImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionPIContent()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_PI_CONTENT = eINSTANCE.getXMLValueFunctionPIContent();

		/**
         * The meta object literal for the '<em><b>Value Function PI</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_PI_CONTENT__VALUE_FUNCTION_PI = eINSTANCE.getXMLValueFunctionPIContent_ValueFunctionPI();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_PI_CONTENT__VALUE_EXPR = eINSTANCE.getXMLValueFunctionPIContent_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableFunctionImpl <em>XML Table Function</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableFunctionImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLTableFunction()
         * @generated
         */
		EClass XML_TABLE_FUNCTION = eINSTANCE.getXMLTableFunction();

		/**
         * The meta object literal for the '<em><b>Table Row Pattern</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_TABLE_FUNCTION__TABLE_ROW_PATTERN = eINSTANCE.getXMLTableFunction_TableRowPattern();

		/**
         * The meta object literal for the '<em><b>Xquery Arg List</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_TABLE_FUNCTION__XQUERY_ARG_LIST = eINSTANCE.getXMLTableFunction_XqueryArgList();

		/**
         * The meta object literal for the '<em><b>Column Def List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_TABLE_FUNCTION__COLUMN_DEF_LIST = eINSTANCE.getXMLTableFunction_ColumnDefList();

		/**
         * The meta object literal for the '<em><b>Namespaces Decl</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_TABLE_FUNCTION__NAMESPACES_DECL = eINSTANCE.getXMLTableFunction_NamespacesDecl();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionTextContentImpl <em>XML Value Function Text Content</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionTextContentImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionTextContent()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_TEXT_CONTENT = eINSTANCE.getXMLValueFunctionTextContent();

		/**
         * The meta object literal for the '<em><b>Value Function Text</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_FUNCTION_TEXT = eINSTANCE.getXMLValueFunctionTextContent_ValueFunctionText();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_TEXT_CONTENT__VALUE_EXPR = eINSTANCE.getXMLValueFunctionTextContent_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateContentImpl <em>XML Value Function Validate Content</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateContentImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateContent()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_VALIDATE_CONTENT = eINSTANCE.getXMLValueFunctionValidateContent();

		/**
         * The meta object literal for the '<em><b>Value Function Validate</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_FUNCTION_VALIDATE = eINSTANCE.getXMLValueFunctionValidateContent_ValueFunctionValidate();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE_CONTENT__VALUE_EXPR = eINSTANCE.getXMLValueFunctionValidateContent_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionItemImpl <em>XML Table Column Definition Item</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionItemImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLTableColumnDefinitionItem()
         * @generated
         */
		EClass XML_TABLE_COLUMN_DEFINITION_ITEM = eINSTANCE.getXMLTableColumnDefinitionItem();

		/**
         * The meta object literal for the '<em><b>Table Function</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION = eINSTANCE.getXMLTableColumnDefinitionItem_TableFunction();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionRegularImpl <em>XML Table Column Definition Regular</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionRegularImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLTableColumnDefinitionRegular()
         * @generated
         */
		EClass XML_TABLE_COLUMN_DEFINITION_REGULAR = eINSTANCE.getXMLTableColumnDefinitionRegular();

		/**
         * The meta object literal for the '<em><b>Data Type</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE = eINSTANCE.getXMLTableColumnDefinitionRegular_DataType();

		/**
         * The meta object literal for the '<em><b>Passing Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION = eINSTANCE.getXMLTableColumnDefinitionRegular_PassingOption();

		/**
         * The meta object literal for the '<em><b>Table Column Pattern</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN = eINSTANCE.getXMLTableColumnDefinitionRegular_TableColumnPattern();

		/**
         * The meta object literal for the '<em><b>Column Definition Default</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT = eINSTANCE.getXMLTableColumnDefinitionRegular_ColumnDefinitionDefault();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionOrdinalityImpl <em>XML Table Column Definition Ordinality</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionOrdinalityImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLTableColumnDefinitionOrdinality()
         * @generated
         */
		EClass XML_TABLE_COLUMN_DEFINITION_ORDINALITY = eINSTANCE.getXMLTableColumnDefinitionOrdinality();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToImpl <em>XML Value Function Validate According To</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateAccordingTo()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO = eINSTANCE.getXMLValueFunctionValidateAccordingTo();

		/**
         * The meta object literal for the '<em><b>Value Function Validate</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALUE_FUNCTION_VALIDATE = eINSTANCE.getXMLValueFunctionValidateAccordingTo_ValueFunctionValidate();

		/**
         * The meta object literal for the '<em><b>Validate Element</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO__VALIDATE_ELEMENT = eINSTANCE.getXMLValueFunctionValidateAccordingTo_ValidateElement();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToURIImpl <em>XML Value Function Validate According To URI</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToURIImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateAccordingToURI()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI = eINSTANCE.getXMLValueFunctionValidateAccordingToURI();

		/**
         * The meta object literal for the '<em><b>No Namespace</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE = eINSTANCE.getXMLValueFunctionValidateAccordingToURI_NoNamespace();

		/**
         * The meta object literal for the '<em><b>Target Namespace URI</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI = eINSTANCE.getXMLValueFunctionValidateAccordingToURI_TargetNamespaceURI();

		/**
         * The meta object literal for the '<em><b>Schema Location URI</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI = eINSTANCE.getXMLValueFunctionValidateAccordingToURI_SchemaLocationURI();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToIdentifierImpl <em>XML Value Function Validate According To Identifier</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToIdentifierImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateAccordingToIdentifier()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER = eINSTANCE.getXMLValueFunctionValidateAccordingToIdentifier();

		/**
         * The meta object literal for the '<em><b>Schema Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME = eINSTANCE.getXMLValueFunctionValidateAccordingToIdentifier_SchemaName();

		/**
         * The meta object literal for the '<em><b>Registered XML Schema Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME = eINSTANCE.getXMLValueFunctionValidateAccordingToIdentifier_RegisteredXMLSchemaName();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNameImpl <em>XML Value Function Validate Element Name</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNameImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateElementName()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME = eINSTANCE.getXMLValueFunctionValidateElementName();

		/**
         * The meta object literal for the '<em><b>Validate Element</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAME__VALIDATE_ELEMENT = eINSTANCE.getXMLValueFunctionValidateElementName_ValidateElement();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNamespaceImpl <em>XML Value Function Validate Element Namespace</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementNamespaceImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateElementNamespace()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE = eINSTANCE.getXMLValueFunctionValidateElementNamespace();

		/**
         * The meta object literal for the '<em><b>No Namespace</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NO_NAMESPACE = eINSTANCE.getXMLValueFunctionValidateElementNamespace_NoNamespace();

		/**
         * The meta object literal for the '<em><b>Namespace URI</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__NAMESPACE_URI = eINSTANCE.getXMLValueFunctionValidateElementNamespace_NamespaceURI();

		/**
         * The meta object literal for the '<em><b>Validate Element</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE_ELEMENT_NAMESPACE__VALIDATE_ELEMENT = eINSTANCE.getXMLValueFunctionValidateElementNamespace_ValidateElement();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespacesDeclarationImpl <em>XML Namespaces Declaration</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLNamespacesDeclarationImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLNamespacesDeclaration()
         * @generated
         */
		EClass XML_NAMESPACES_DECLARATION = eINSTANCE.getXMLNamespacesDeclaration();

		/**
         * The meta object literal for the '<em><b>Namespace Decltem List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_NAMESPACES_DECLARATION__NAMESPACE_DECLTEM_LIST = eINSTANCE.getXMLNamespacesDeclaration_NamespaceDecltemList();

		/**
         * The meta object literal for the '<em><b>Value Function Element</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_ELEMENT = eINSTANCE.getXMLNamespacesDeclaration_ValueFunctionElement();

		/**
         * The meta object literal for the '<em><b>Value Function Forest</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_NAMESPACES_DECLARATION__VALUE_FUNCTION_FOREST = eINSTANCE.getXMLNamespacesDeclaration_ValueFunctionForest();

		/**
         * The meta object literal for the '<em><b>Table Function</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_NAMESPACES_DECLARATION__TABLE_FUNCTION = eINSTANCE.getXMLNamespacesDeclaration_TableFunction();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributesDeclarationImpl <em>XML Attributes Declaration</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLAttributesDeclarationImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLAttributesDeclaration()
         * @generated
         */
		EClass XML_ATTRIBUTES_DECLARATION = eINSTANCE.getXMLAttributesDeclaration();

		/**
         * The meta object literal for the '<em><b>Value Function Element</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_ATTRIBUTES_DECLARATION__VALUE_FUNCTION_ELEMENT = eINSTANCE.getXMLAttributesDeclaration_ValueFunctionElement();

		/**
         * The meta object literal for the '<em><b>Attribute Decl Item</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_ATTRIBUTES_DECLARATION__ATTRIBUTE_DECL_ITEM = eINSTANCE.getXMLAttributesDeclaration_AttributeDeclItem();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentListImpl <em>XML Value Function Element Content List</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionElementContentListImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionElementContentList()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST = eINSTANCE.getXMLValueFunctionElementContentList();

		/**
         * The meta object literal for the '<em><b>Null Handling Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__NULL_HANDLING_OPTION = eINSTANCE.getXMLValueFunctionElementContentList_NullHandlingOption();

		/**
         * The meta object literal for the '<em><b>Value Function Element</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__VALUE_FUNCTION_ELEMENT = eINSTANCE.getXMLValueFunctionElementContentList_ValueFunctionElement();

		/**
         * The meta object literal for the '<em><b>Element Content List Children</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_ELEMENT_CONTENT_LIST__ELEMENT_CONTENT_LIST_CHILDREN = eINSTANCE.getXMLValueFunctionElementContentList_ElementContentListChildren();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryReturningImpl <em>XML Value Function Query Returning</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryReturningImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionQueryReturning()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_QUERY_RETURNING = eINSTANCE.getXMLValueFunctionQueryReturning();

		/**
         * The meta object literal for the '<em><b>Returning Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_QUERY_RETURNING__RETURNING_OPTION = eINSTANCE.getXMLValueFunctionQueryReturning_ReturningOption();

		/**
         * The meta object literal for the '<em><b>Passing Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_VALUE_FUNCTION_QUERY_RETURNING__PASSING_OPTION = eINSTANCE.getXMLValueFunctionQueryReturning_PassingOption();

		/**
         * The meta object literal for the '<em><b>Value Function Query</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY = eINSTANCE.getXMLValueFunctionQueryReturning_ValueFunctionQuery();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementImpl <em>XML Value Function Validate Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateElementImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLValueFunctionValidateElement()
         * @generated
         */
		EClass XML_VALUE_FUNCTION_VALIDATE_ELEMENT = eINSTANCE.getXMLValueFunctionValidateElement();

		/**
         * The meta object literal for the '<em><b>Validate Element Namespace</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAMESPACE = eINSTANCE.getXMLValueFunctionValidateElement_ValidateElementNamespace();

		/**
         * The meta object literal for the '<em><b>Validate Element Name</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ELEMENT_NAME = eINSTANCE.getXMLValueFunctionValidateElement_ValidateElementName();

		/**
         * The meta object literal for the '<em><b>Validate According To</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_VALUE_FUNCTION_VALIDATE_ELEMENT__VALIDATE_ACCORDING_TO = eINSTANCE.getXMLValueFunctionValidateElement_ValidateAccordingTo();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionDefaultImpl <em>XML Table Column Definition Default</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionDefaultImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLTableColumnDefinitionDefault()
         * @generated
         */
		EClass XML_TABLE_COLUMN_DEFINITION_DEFAULT = eINSTANCE.getXMLTableColumnDefinitionDefault();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_TABLE_COLUMN_DEFINITION_DEFAULT__VALUE_EXPR = eINSTANCE.getXMLTableColumnDefinitionDefault_ValueExpr();

		/**
         * The meta object literal for the '<em><b>Column Definition Regular</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference XML_TABLE_COLUMN_DEFINITION_DEFAULT__COLUMN_DEFINITION_REGULAR = eINSTANCE.getXMLTableColumnDefinitionDefault_ColumnDefinitionRegular();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionEncodingImpl <em>XML Serialize Function Encoding</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionEncodingImpl
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLSerializeFunctionEncoding()
         * @generated
         */
		EClass XML_SERIALIZE_FUNCTION_ENCODING = eINSTANCE.getXMLSerializeFunctionEncoding();

		/**
         * The meta object literal for the '<em><b>Encoding Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute XML_SERIALIZE_FUNCTION_ENCODING__ENCODING_NAME = eINSTANCE.getXMLSerializeFunctionEncoding_EncodingName();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType <em>XML Passing Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLPassingType()
         * @generated
         */
		EEnum XML_PASSING_TYPE = eINSTANCE.getXMLPassingType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType <em>XML Content Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLContentType()
         * @generated
         */
		EEnum XML_CONTENT_TYPE = eINSTANCE.getXMLContentType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType <em>XML Declaration Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLDeclarationType()
         * @generated
         */
		EEnum XML_DECLARATION_TYPE = eINSTANCE.getXMLDeclarationType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType <em>XML Returning Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLReturningType()
         * @generated
         */
		EEnum XML_RETURNING_TYPE = eINSTANCE.getXMLReturningType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType <em>XML Null Handling Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLNullHandlingType
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLNullHandlingType()
         * @generated
         */
		EEnum XML_NULL_HANDLING_TYPE = eINSTANCE.getXMLNullHandlingType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType <em>XML Whitespace Handling Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLWhitespaceHandlingType
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLWhitespaceHandlingType()
         * @generated
         */
		EEnum XML_WHITESPACE_HANDLING_TYPE = eINSTANCE.getXMLWhitespaceHandlingType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType <em>XML Empty Handling Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLEmptyHandlingType
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLEmptyHandlingType()
         * @generated
         */
		EEnum XML_EMPTY_HANDLING_TYPE = eINSTANCE.getXMLEmptyHandlingType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2 <em>XML Content Type2</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType2
         * @see org.eclipse.datatools.modelbase.sql.xml.query.impl.SQLXMLQueryModelPackageImpl#getXMLContentType2()
         * @generated
         */
		EEnum XML_CONTENT_TYPE2 = eINSTANCE.getXMLContentType2();

	}

} //SQLXMLQueryPackage
